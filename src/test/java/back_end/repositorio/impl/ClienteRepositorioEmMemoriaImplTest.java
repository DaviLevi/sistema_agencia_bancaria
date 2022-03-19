package back_end.repositorio.impl;

import back_end.dominio.*;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.FuncionarioRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ClienteRepositorioEmMemoriaImplTest {

    private static ClienteRepositorio clienteRepositorio;
    private static FuncionarioRepositorio funcionarioRepositorio;

    private static Funcionario gerenteSalvo;
    private static Agencia agenciaSalva;

    @BeforeAll
    public static void setup(){
        AgenciaRepositorio agenciaRepositorio = new AgenciaRepositorioEmMemoriaImpl();
        funcionarioRepositorio = new FuncionarioRepositorioEmMemoriaImpl(agenciaRepositorio);
        clienteRepositorio = new ClienteRepositorioEmMemoriaImpl(funcionarioRepositorio);

        agenciaSalva = new Agencia(1,"Uberlandia","MG");
        agenciaRepositorio.salva(agenciaSalva);

        gerenteSalvo = new Funcionario(1, "Gerente Massa Test", "123456",
                Set.of(), null, LocalDate.now(), agenciaSalva);

        funcionarioRepositorio.salva(gerenteSalvo);
    }


    @Test
    public void deveConseguirSalvarCorretamente(){
        Funcionario referenciaGerente = new Funcionario(1);

        Cliente novoCliente = new Cliente(
        "Davide Sgalambro", "01606156233",  LocalDate.parse("07/04/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,
                "Av. Segismundo Pereira 1145",
                "Uberlandia", "MG", referenciaGerente
        );

        Cliente clienteSalvo = clienteRepositorio.salva(novoCliente);

        Assertions.assertNotNull(clienteSalvo);
        Assertions.assertEquals(novoCliente, clienteSalvo);
        Assertions.assertEquals(gerenteSalvo, clienteSalvo.getGerente());
    }

    @Test
    public void naoDeveConseguirSalvarGerenteInexistente(){
        String mensagemEsperada = "Gerente vinculado ao cliente nao existe";
        ClienteRepositorio repositorio = new ClienteRepositorioEmMemoriaImpl(funcionarioRepositorio);

        Funcionario referenciaGerente = new Funcionario(2);

        IllegalStateException ex = Assertions.assertThrows(IllegalStateException.class, () -> {
            Cliente novoCliente = new Cliente(
                    "Davide Sgalambro", "01606156233",  LocalDate.parse("07/04/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,
                    "Av. Segismundo Pereira 1145",
                    "Uberlandia", "MG", referenciaGerente
            );

            repositorio.salva(novoCliente);
        });

        Assertions.assertNotNull(ex);
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }
}
