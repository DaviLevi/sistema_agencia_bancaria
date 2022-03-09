package back_end.repositorio.impl;

import back_end.dominio.Cliente;
import back_end.dominio.Funcionario;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.FuncionarioRepositorio;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ClienteRepositorioEmMemoriaImpl implements ClienteRepositorio {

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
        Funcionario gerenteConsultado = funcionarioRepositorio.buscaFuncionarioPorNumeroFuncional(cliente.getNumeroFuncionalGerente())
                                                              .orElseThrow(() -> new IllegalStateException("Gerente vinculado ao cliente nao existe"));

        cliente.atribuiGerente(gerenteConsultado);
        clientes.put(idDisponivel, cliente);
        geradorId.incrementAndGet();
        return cliente;
    }

    @Override
    public Cliente atualiza(Cliente cliente) {
        return null;
    }
}
