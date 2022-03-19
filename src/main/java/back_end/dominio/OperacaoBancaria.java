package back_end.dominio;

import java.time.LocalDate;

public class OperacaoBancaria {
    private Long id;
    private Conta conta;
    private TipoOperacaoBancaria tipo;
    private String descricao;
    private Double valor;
    private LocalDate dataRealizacao;


    public enum TipoOperacaoBancaria{
        CREDITO, DEBITO
    }

}
