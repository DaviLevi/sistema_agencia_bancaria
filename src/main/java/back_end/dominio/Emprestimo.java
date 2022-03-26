package back_end.dominio;


import java.io.Serializable;
import java.util.Set;

public class Emprestimo implements Serializable {
    private Long id;
    private Set<Cliente> clientes;
    private Agencia agencia;
    private Double valor;
    private Integer quantidadeParcelas;

    public Emprestimo(Long id, Set<Cliente> clientes, Agencia agencia, Double valor, Integer qtdParcelas){
        this.id = id;
        this.clientes = clientes;
        this.agencia = agencia;
        this.valor = valor;
        this.quantidadeParcelas = qtdParcelas;
    }

}
