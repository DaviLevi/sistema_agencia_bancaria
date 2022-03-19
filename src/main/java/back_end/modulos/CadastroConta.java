package back_end.modulos;

import back_end.dominio.Agencia;
import back_end.dominio.Conta;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.ContaRepositorio;

public class CadastroConta {

    private ContaRepositorio contaRepositorio;
    private AgenciaRepositorio agenciaRepositorio;
    private ClienteRepositorio clienteRepositorio;

    public CadastroConta(ContaRepositorio contaRepositorio, AgenciaRepositorio agenciaRepositorio, ClienteRepositorio clienteRepositorio) {
        this.contaRepositorio = contaRepositorio;
        this.agenciaRepositorio = agenciaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    public void cadastra(Conta conta){
        Agencia agenciaConsultada = agenciaRepositorio.consultaAgenciaPeloNumeroAgencia(conta.getNumeroAgencia()).orElseThrow(() -> new IllegalStateException("Agencia inexistente : Impossivel cadastrar a conta"));
        conta.getProprietarios().forEach(p -> {
            clienteRepositorio.salva(p);
        });
        conta.atualizaAgencia(agenciaConsultada);
        contaRepositorio.salva(conta);
    }

}
