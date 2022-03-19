package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Funcionario;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public class FuncionarioRepositorioEmMemoriaImplTest {

    private static AgenciaRepositorio agenciaRepositorio;
    private static FuncionarioRepositorio funcionarioRepositorio;

    @BeforeAll
    public static void setup(){
        agenciaRepositorio = new AgenciaRepositorioEmMemoriaImpl();

        Agencia agencia = new Agencia(1,"Uberlandia","MG");
        agenciaRepositorio.salva(agencia);

        funcionarioRepositorio = new FuncionarioRepositorioEmMemoriaImpl(agenciaRepositorio);

        Funcionario supervisor = new Funcionario(1, "Gerente Massa Test", "123456",
                Set.of(), null, LocalDate.now(), agencia);

        Funcionario funcionarioSalvo = funcionarioRepositorio.salva(supervisor);
    }

    @Test
    public void deveConseguirSalvarCorretamente(){
        Agencia agenciaReferencia = new Agencia(1);

        Funcionario supervisor = new Funcionario(123, "Gerente", "123456",
                                                Set.of(), null, LocalDate.now(), agenciaReferencia);

        Funcionario funcionarioSalvo = funcionarioRepositorio.salva(supervisor);
        Agencia agenciaSalva = funcionarioSalvo.getAgencia();

        Optional<Agencia> agenciaNoBd = agenciaRepositorio.consultaAgenciaPeloNumeroAgencia(1);

        Assertions.assertTrue(agenciaNoBd.isPresent());
        Assertions.assertEquals(agenciaSalva, agenciaNoBd.get());
    }

    @Test
    public void naoDeveSalvarSeAgenciaNaoExistir(){
        String mensagemEsperada = "Agencia vinculada ao funcionario nao existe";
        Agencia agenciaReferencia = new Agencia(2);

        Funcionario supervisor = new Funcionario(123, "Gerente", "123456",
                Set.of(), null, LocalDate.now(), agenciaReferencia);

        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            funcionarioRepositorio.salva(supervisor);
        });

        Assertions.assertNotNull(ex);
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    public void naoDeveSalvarSupervisorInexistente(){
        String mensagemEsperada = "Supervisor inexistente";
        Agencia agenciaReferencia = new Agencia(1);
        Funcionario supervisorReferencia = new Funcionario(2);

        Funcionario supervisor = new Funcionario(123, "Gerente", "123456",
                Set.of(), supervisorReferencia, LocalDate.now(), agenciaReferencia);

        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            funcionarioRepositorio.salva(supervisor);
        });

        Assertions.assertNotNull(ex);
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }

    @Test
    public void naoDeveSalvarFuncionarioDuplicado(){
        String mensagemEsperada = "Ja Existe um funcionario com mesmo numero funcional no BD";
        Agencia agenciaReferencia = new Agencia(1);
        Funcionario supervisorReferencia = new Funcionario(1);

        Funcionario supervisor = new Funcionario(1, "Gerente", "123456",
                Set.of(), supervisorReferencia, LocalDate.now(), agenciaReferencia);

        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            funcionarioRepositorio.salva(supervisor);
        });

        Assertions.assertNotNull(ex);
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }
}
