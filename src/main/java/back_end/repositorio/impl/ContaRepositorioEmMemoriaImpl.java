package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.dominio.Conta;
import back_end.recuperacao.Recuperavel;
import back_end.repositorio.ContaRepositorio;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class ContaRepositorioEmMemoriaImpl implements ContaRepositorio, Recuperavel {

    private Map<Long, Conta> contas;

    public ContaRepositorioEmMemoriaImpl(){
        this.contas = new Hashtable<>();
    }

    @Override
    public void salva(Conta conta) {
        boolean existeContaComMesmoIdNoBd = contas.containsKey(conta.getId());
        if(existeContaComMesmoIdNoBd) throw new IllegalStateException("Ja Existe uma conta com o mesmo id no BD");
        contas.put(conta.getId(), conta);
    }

    @Override
    public void atualiza(Conta conta) {
        boolean naoExisteContaComMesmoIdNoBd = !contas.containsKey(conta.getId());
        if(naoExisteContaComMesmoIdNoBd) throw new IllegalStateException("Nao existe uma conta com o id " + conta.getId() + " no BD");
        contas.put(conta.getId(), conta);
    }

    @Override
    public void exclui(Conta conta) {
        boolean naoExisteContaComMesmoIdNoBd = !contas.containsKey(conta.getId());
        if(naoExisteContaComMesmoIdNoBd) throw new IllegalStateException("Nao existe uma conta com o id " + conta.getId() + " no BD");
        contas.remove(conta.getId());
    }

    @Override
    public Optional<Conta> buscaContaPor(Long id) {
        return contas.keySet().stream().filter(k -> k.equals(id)).findAny().map(pk -> contas.get(pk));
    }

    @Override
    public void realizaBackup() {
        try
        {
            String pathToAgenciaDb = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "java" +
                    System.getProperty("file.separator") + "DB" +
                    System.getProperty("file.separator") + "contas.db";

            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(pathToAgenciaDb);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this.contas);

            out.close();
            file.close();

            System.out.println("O Backup do repositorio Conta foi realizado com sucesso!");

        }
        catch(IOException ex)
        {
            System.out.println("Falha ao realizar o Backup do repositorio Conta!");
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
                    System.getProperty("file.separator") + "contas.db";

            // Reading the object from a file
            FileInputStream file = new FileInputStream(pathToAgenciaDb);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            this.contas = (Map<Long, Conta>) in.readObject();

            in.close();
            file.close();

            System.out.println("O repositorio Conta foi recuperado com sucesso!");
        }
        catch(Exception ex)
        {
            System.out.println("Falha ao recuperar o repositorio Conta : " + ex.getMessage());
        }
    }
}
