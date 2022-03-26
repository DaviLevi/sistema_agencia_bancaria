package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.dominio.Funcionario;
import back_end.recuperacao.Recuperavel;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.FuncionarioRepositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ClienteRepositorioEmMemoriaImpl implements ClienteRepositorio, Recuperavel {

    private Map<Long, Cliente> clientes;
    private FuncionarioRepositorio funcionarioRepositorio;
    private AtomicLong geradorId;

    public ClienteRepositorioEmMemoriaImpl(FuncionarioRepositorio funcionarioRepositorio){
        this.clientes = new Hashtable<>();
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.geradorId = new AtomicLong(1);
    }

    @Override
    public Cliente salva(Cliente cliente) {
        Long idDisponivel = geradorId.get();
        cliente.atualizaId(idDisponivel);
        //Funcionario gerenteConsultado = funcionarioRepositorio.buscaFuncionarioPorNumeroFuncional(cliente.getNumeroFuncionalGerente())
        //                                                      .orElseThrow(() -> new IllegalStateException("Gerente vinculado ao cliente nao existe"));

        //cliente.atribuiGerente(gerenteConsultado);
        cliente.atribuiGerente(cliente.getGerente());
        clientes.put(idDisponivel, cliente);
        geradorId.incrementAndGet();
        return cliente;
    }
    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        return null;
    }

    @Override
    public void realizaBackup() {
        try
        {
            String pathToAgenciaDb = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "java" +
                    System.getProperty("file.separator") + "DB" +
                    System.getProperty("file.separator") + "clientes.db";

            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(pathToAgenciaDb);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this.clientes);

            out.close();
            file.close();

            System.out.println("O Backup do repositorio Cliente foi realizado com sucesso!");

        }
        catch(IOException ex)
        {
            System.out.println("Falha ao realizar o Backup do repositorio Cliente!");
        }
    }

    @Override
    public void recuperaBackup() {
        // Deserialization
        try
        {

            String pathToAgenciaDb = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "java" +
                    System.getProperty("file.separator") + "DB" +
                    System.getProperty("file.separator") + "clientes.db";

            // Reading the object from a file
            FileInputStream file = new FileInputStream(pathToAgenciaDb);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            this.clientes = (Map<Long, Cliente>) in.readObject();

            in.close();
            file.close();

            System.out.println("O repositorio Cliente foi recuperado com sucesso!");
        }
        catch(Exception ex)
        {
            System.out.println("Falha ao recuperar o repositorio Cliente : " + ex.getMessage());
        }
    }
}
