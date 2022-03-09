package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class AgenciaRepositorioEmMemoriaImplTest {


    @Test
    public void deveConseguirSalvarCorretamente(){
        AgenciaRepositorio repositorio = new AgenciaRepositorioEmMemoriaImpl();

        Agencia agencia = new Agencia(
                1,
            "Uberlandia",
            "MG"
        );

        repositorio.salva(agencia);
        Optional<Agencia> agenciaBuscada = repositorio.consultaAgenciaPeloNumeroAgencia(1);

        Assertions.assertTrue(agenciaBuscada.isPresent());
    }

    @Test
    public void deveConseguirEncontrarAgenciaComMesmoNome(){
        AgenciaRepositorio repositorio = new AgenciaRepositorioEmMemoriaImpl();

        Agencia agencia = new Agencia(
                1,
                "Uberlandia",
                "MG"
        );

        repositorio.salva(agencia);

        Assertions.assertTrue(repositorio.existeUmaAgenciaComNumeroAgencia(1));
    }

    @Test
    public void naoDeveConseguirSalvarAgenciaDuplicada(){
        String mensagemEsperada = "Ja Existe uma agencia com mesmo numero agencia no BD";
        AgenciaRepositorio repositorio = new AgenciaRepositorioEmMemoriaImpl();

        Agencia agencia = new Agencia(
                1,
                "Uberlandia",
                "MG"
        );
        repositorio.salva(agencia);


        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            Agencia agenciaDuplicada = new Agencia(
                    1,
                    "Uberlandia",
                    "MG"
            );
            repositorio.salva(agenciaDuplicada);
        });

        Assertions.assertNotNull(ex);
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }

}
