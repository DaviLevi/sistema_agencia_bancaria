package back_end.modulos;

import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.impl.AgenciaRepositorioEmMemoriaImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CadastroAgenciaTest {


    @Test
    public void deveCadastrarAgencia(){
        AgenciaRepositorio repositorio = new AgenciaRepositorioEmMemoriaImpl();
        CadastroAgencia cadastrador = new CadastroAgencia(repositorio);

        Agencia agencia = new Agencia(
                1,
                "Uberlandia",
                "MG"
        );

        Agencia agenciaSalva = cadastrador.cadastra(agencia);

        Assertions.assertTrue(repositorio.existeUmaAgenciaComNumeroAgencia(1));

    }

    @Test
    public void deveAtualizarAgencia(){
        Agencia agenciaAntesDaAtualizacao = new Agencia(1,"Uberlandia","MG");
        String cidadeEsperada = "Uberaba";

        AgenciaRepositorio repositorio = new AgenciaRepositorioEmMemoriaImpl();
        CadastroAgencia cadastrador = new CadastroAgencia(repositorio);

        cadastrador.cadastra(agenciaAntesDaAtualizacao);

        Agencia agenciaDepoisDaAtualizacao = agenciaAntesDaAtualizacao.atualizaCidade(cidadeEsperada);
        cadastrador.atualiza(agenciaDepoisDaAtualizacao);

        Optional<Agencia> agenciaConsultadaAposAtualizacao = repositorio.consultaAgenciaPeloNumeroAgencia(1);

        Assertions.assertTrue(agenciaConsultadaAposAtualizacao.isPresent());
        Assertions.assertTrue(agenciaConsultadaAposAtualizacao.get().encontraseNaCidade(cidadeEsperada));
    }


}
