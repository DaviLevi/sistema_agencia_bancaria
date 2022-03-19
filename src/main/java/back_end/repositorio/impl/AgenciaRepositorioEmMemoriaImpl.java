package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;

import java.io.*;
import java.util.*;

import static DB.Path.DB_path;

public class AgenciaRepositorioEmMemoriaImpl implements AgenciaRepositorio {

    private Map<Integer, Agencia> agencias;

    public AgenciaRepositorioEmMemoriaImpl(){
        this.agencias = new Hashtable<>();
    }

    @Override
    public Agencia salva(Agencia novaAgencia) {
        boolean existeAgenciaComMesmoNumeroNoBd = agencias.containsKey(novaAgencia.getNumeroAgencia());
        if(existeAgenciaComMesmoNumeroNoBd) throw new IllegalStateException("Ja Existe uma agencia com mesmo numero agencia no BD");
        agencias.put(novaAgencia.getNumeroAgencia(), novaAgencia);
        try{
            FileOutputStream arq = new FileOutputStream(DB_path);
            ObjectOutputStream os = new ObjectOutputStream(arq);
            for(int i =0 ; i < agencias.size(); i++){
                os.writeObject(agencias.get(i));
            }
            os.close();
            arq.close();
            System.out.println("Foi escrito lek!");
        }catch(IOException erro){
            System.out.println("Deu erro -> "+ erro);
        }
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

    public ArrayList<Agencia> ler(){
        ArrayList<Agencia> agencias = new ArrayList<>();
        try{
            FileInputStream arq = new FileInputStream(DB_path);
            ObjectInputStream is = new ObjectInputStream(arq);
            for(int i = 0; i < 4; i++){
                Agencia age = (Agencia) is.readObject();
                agencias.add(age);
            }
            is.close();arq.close();
        }catch (IOException erro){
            System.out.println("DEu errro ->" + erro);
        } catch (ClassNotFoundException e) {
            System.out.println("Deu erro no ob" + e);
        }
        return agencias;
    }
}
