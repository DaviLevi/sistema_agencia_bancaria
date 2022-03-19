package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.dominio.Funcionario;
import back_end.recuperacao.Recuperavel;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class FuncionarioRepositorioEmMemoriaImpl implements FuncionarioRepositorio, Recuperavel {
    private Map<Integer, Funcionario> funcionarios;
    private AgenciaRepositorio agenciaRepositorio;

    public FuncionarioRepositorioEmMemoriaImpl(AgenciaRepositorio agenciaRepositorio){
        this.funcionarios = new Hashtable<>();
        this.agenciaRepositorio = agenciaRepositorio;
    }

    @Override
    public Funcionario salva(Funcionario novoFuncionario) {
        Agencia agencia = agenciaRepositorio.consultaAgenciaPeloNumeroAgencia(novoFuncionario.getNumeroAgencia()).orElseThrow(() -> new IllegalStateException("Agencia vinculada ao funcionario nao existe"));
        if(!novoFuncionario.ehGerente()){
            boolean naoExisteSupervisor = ! funcionarios.containsKey(novoFuncionario.getNumeroFuncionalDoSupervisor());
            if(naoExisteSupervisor) throw new IllegalStateException("Supervisor inexistente");
        }
        boolean existeFuncionarioComMesmoNumeroFuncionalNoBd = funcionarios.containsKey(novoFuncionario.getNumeroFuncional());
        if(existeFuncionarioComMesmoNumeroFuncionalNoBd) throw new IllegalStateException("Ja Existe um funcionario com mesmo numero funcional no BD");
        novoFuncionario.atualizaAgencia(agencia);
        funcionarios.put(novoFuncionario.getNumeroFuncional(), novoFuncionario);
        return novoFuncionario;
    }

    @Override
    public Optional<Funcionario> buscaFuncionarioPorNumeroFuncional(Integer numeroFuncional) {
        return funcionarios.keySet().stream().filter(nf -> nf.equals(numeroFuncional)).findFirst().map(nf -> funcionarios.get(nf));
    }

    @Override
    public void realizaBackup() {
        try
        {
            String pathToAgenciaDb = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "java" +
                    System.getProperty("file.separator") + "DB" +
                    System.getProperty("file.separator") + "funcionarios.db";
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(pathToAgenciaDb);
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(this.funcionarios);
            out.close();
            file.close();
            System.out.println("O Backup do repositorio Funcionario foi realizado com sucesso!");
        }
        catch(IOException ex)
        {
            System.out.println("Falha ao realizar o Backup do repositorio Funcionario!");
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
                    System.getProperty("file.separator") + "funcionarios.db";

            // Reading the object from a file
            FileInputStream file = new FileInputStream(pathToAgenciaDb);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            this.funcionarios = (Map<Integer, Funcionario>) in.readObject();
            in.close();
            file.close();
            System.out.println("O repositorio Funcionario foi recuperado com sucesso!");
        }
        catch(Exception ex)
        {
            System.out.println("Falha ao recuperar o repositorio Funcionario : " + ex.getMessage());
        }
    }
}
