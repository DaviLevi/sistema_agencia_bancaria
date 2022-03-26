package back_end.repositorio;

import back_end.dominio.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositorio {
    Funcionario salva(Funcionario salva);
    Optional<Funcionario> buscaFuncionarioPorNumeroFuncional(Integer numeroFuncional);
    List<Funcionario> listar();
}
