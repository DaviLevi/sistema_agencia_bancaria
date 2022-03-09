package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

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
}
