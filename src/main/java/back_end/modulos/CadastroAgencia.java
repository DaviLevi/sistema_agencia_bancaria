package back_end.modulos;

import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;

import java.util.List;

public class CadastroAgencia {
    private AgenciaRepositorio repositorio;

    public CadastroAgencia(AgenciaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Agencia cadastra(Agencia novaAgencia){
        return repositorio.salva(novaAgencia);
    }

    public Agencia atualiza(Agencia agencia){
        repositorio.consultaAgenciaPeloNumeroAgencia(agencia.getNumeroAgencia())
                                     .orElseThrow(() -> new IllegalStateException("Nao existe uma agencia com id " + agencia.getNumeroAgencia()));
        return repositorio.atualiza(agencia);
    }

    public List<Agencia> listar(){
        return repositorio.listar();
    }

}
