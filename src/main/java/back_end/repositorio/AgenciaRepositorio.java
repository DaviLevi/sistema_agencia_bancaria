package back_end.repositorio;

import back_end.dominio.Agencia;

import java.util.Optional;

public interface AgenciaRepositorio {
    Agencia salva(Agencia agencia);
    Agencia atualiza(Agencia agenciaSalva);
    Optional<Agencia> consultaAgenciaPeloNumeroAgencia(Integer numeroAgencia);
    boolean existeUmaAgenciaComNumeroAgencia(Integer numeroAgencia);
}
