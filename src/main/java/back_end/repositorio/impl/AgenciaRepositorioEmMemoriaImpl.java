package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.recuperacao.Recuperavel;
import back_end.repositorio.AgenciaRepositorio;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static DB.Path.DB_path;

public class AgenciaRepositorioEmMemoriaImpl implements AgenciaRepositorio, Recuperavel {

    private Map<Integer, Agencia> agencias;

    public AgenciaRepositorioEmMemoriaImpl(){
        this.agencias = new Hashtable<>();
    }

    @Override
    public Agencia salva(Agencia novaAgencia) {
        boolean existeAgenciaComMesmoNumeroNoBd = agencias.containsKey(novaAgencia.getNumeroAgencia());
        if(existeAgenciaComMesmoNumeroNoBd) throw new IllegalStateException("Ja Existe uma agencia com mesmo numero agencia no BD");
        agencias.put(novaAgencia.getNumeroAgencia(), novaAgencia);
        return novaAgencia;
    }

    @Override
    public Agencia atualiza(Agencia agenciaSalva) {
        agencias.put(agenciaSalva.getNumeroAgencia(), agenciaSalva);
        return agenciaSalva;
    }

    @Override
    public Optional<Agencia> consultaAgenciaPeloNumeroAgencia(Integer numeroAgencia) {
        return agencias.keySet().stream().filter(k -> k.equals(numeroAgencia)).findAny().map(pk -> agencias.get(pk));
    }

    @Override
    public boolean existeUmaAgenciaComNumeroAgencia(Integer numeroAgencia) {
        return agencias.values().stream().anyMatch(a -> a.possuiNumeroAgencia(numeroAgencia));
    }

    @Override
    public List<Agencia> listar() {
        return new ArrayList<>(agencias.values());
    }

    @Override
    public void realizaBackup() {
        try
        {

            String pathToAgenciaDb = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
                    System.getProperty("file.separator") + "main" +
                    System.getProperty("file.separator") + "java" +
                    System.getProperty("file.separator") + "DB" +
                    System.getProperty("file.separator") + "agencias.db";

            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(pathToAgenciaDb);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(agencias);

            out.close();
            file.close();

            System.out.println("O Backup do repositorio Agencia foi realizado com sucesso!");

        }
        catch(IOException ex)
        {
            System.out.println("Falha ao realizar o Backup do repositorio Agencia!");
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
                    System.getProperty("file.separator") + "agencias.db";

            // Reading the object from a file
            FileInputStream file = new FileInputStream(pathToAgenciaDb);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            this.agencias = (Map<Integer, Agencia>) in.readObject();

            in.close();
            file.close();

            System.out.println("O repositorio Agencia foi recuperado com sucesso!");
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught : " + ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
