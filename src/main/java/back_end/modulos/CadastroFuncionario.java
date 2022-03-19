package back_end.modulos;

import back_end.dominio.Funcionario;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;

public class CadastroFuncionario {

    private FuncionarioRepositorio funcionarioRepositorio;
    private AgenciaRepositorio agenciaRepositorio;

    public CadastroFuncionario(FuncionarioRepositorio funcionarioRepositorio, AgenciaRepositorio agenciaRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.agenciaRepositorio = agenciaRepositorio;
    }

    public void cadastra(Funcionario funcionario){
        agenciaRepositorio.consultaAgenciaPeloNumeroAgencia(funcionario.getNumeroAgencia()).orElseThrow(() -> new IllegalStateException("A agencia atribuida ao funcionario nao existe"));
        funcionarioRepositorio.salva(funcionario);
    }
}
