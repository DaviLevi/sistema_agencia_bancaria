package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Conta;
import back_end.repositorio.ContaRepositorio;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class ContaRepositorioEmMemoriaImpl implements ContaRepositorio {

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
}
