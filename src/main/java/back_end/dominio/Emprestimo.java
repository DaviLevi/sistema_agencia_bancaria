package back_end.dominio;


import java.util.Set;

public class Emprestimo {
    private Long id;
    private Set<Cliente> clientes;
    private Agencia agencia;
    private Double valor;
    private Integer quantidadeParcelas;
}
