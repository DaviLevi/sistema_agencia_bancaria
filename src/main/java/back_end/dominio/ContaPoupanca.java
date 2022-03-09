package back_end.dominio;

import back_end.dominio.Agencia;

import java.time.LocalDate;
import java.util.Set;

public class ContaPoupanca extends Conta{
    private Double taxaJuros;

    public ContaPoupanca(Set<Cliente> proprietarios, Long numeroIdentificacao,
                         Agencia agencia, LocalDate dataCriacao,
                         Double saldo, LocalDate dataUltimoAcesso,
                         Double taxaJuros) {
        super(proprietarios, numeroIdentificacao, agencia, dataCriacao, saldo, dataUltimoAcesso);
        this.taxaJuros = taxaJuros;
    }
}
