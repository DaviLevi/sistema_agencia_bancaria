package back_end.dominio;

import java.io.Serializable;
import java.time.LocalDate;

public class OperacaoBancaria implements Serializable {
    private Long id;
    private Conta conta;
    private String tipo;
    private String descricao;
    private Double valor;
    private LocalDate dataRealizacao;

    public OperacaoBancaria(Long id, Conta conta, String Tipo, String descricao, Double Valor){
        this.id = id;
        this.conta = conta;
        this.tipo = Tipo;
        this.descricao = descricao;
        this.valor = Valor;
        this.dataRealizacao = LocalDate.now();
    }

}
