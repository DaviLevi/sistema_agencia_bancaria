package back_end.dominio;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public abstract class Conta  implements Serializable {

    protected Set<Cliente> proprietarios;
    protected Long id;
    protected Agencia agencia;
    protected LocalDate dataCriacao;
    protected Double saldo;
    protected LocalDate dataUltimoAcesso;

    public Conta(Set<Cliente> proprietarios, Long id, Agencia agencia, LocalDate dataCriacao, Double saldo, LocalDate dataUltimoAcesso) {
        this.proprietarios = proprietarios;
        this.id = id;
        this.agencia = agencia;
        this.dataCriacao = dataCriacao;
        this.saldo = saldo;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.proprietarios.forEach(p -> p.atribuiConta(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id) && Objects.equals(agencia, conta.agencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agencia);
    }

    public Long getId(){
        return this.id;
    }

    public void atualizaid(Long id){
        this.id = id;
    }
    public Set<Cliente> getProprietarios(){
        return this.proprietarios;
    }

    public void atualizaAgencia(Agencia novaAgencia){
        this.agencia = novaAgencia;
    }

    public Integer getNumeroAgencia(){ return agencia.getNumeroAgencia(); }

    public Double getSaldo() {
        return saldo;
    }
    public Integer getAgencia() {
        return agencia.getNumeroAgencia();
    }
    public LocalDate getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }
    public void setDataUltimoAcesso(LocalDate date){
        this.dataUltimoAcesso = date;
    }
}
