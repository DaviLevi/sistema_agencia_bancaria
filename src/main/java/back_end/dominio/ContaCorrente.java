package back_end.dominio;

import back_end.dominio.Agencia;

import java.time.LocalDate;
import java.util.Set;

public class ContaCorrente extends Conta{
    private Double tarifaMensal;

    public ContaCorrente(Set<Cliente> proprietarios, Long numeroIdentificacao,
                         Agencia agencia, LocalDate dataCriacao, Double saldo,
                         LocalDate dataUltimoAcesso, Double tarifaMensal
                         ) {
        super(proprietarios, numeroIdentificacao, agencia, dataCriacao, saldo, dataUltimoAcesso);
        this.tarifaMensal = tarifaMensal;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia=" + agencia +
                "id=" + id +
                '}';
    }
}
