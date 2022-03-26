package back_end.repositorio;

import back_end.dominio.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaRepositorio {
    void salva(Conta conta);
    void atualiza(Conta conta);
    void exclui(Conta conta);
    Optional<Conta> buscaContaPor(Long id);
    List<Conta> listar();
}
