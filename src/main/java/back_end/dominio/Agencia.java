package back_end.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Agencia implements Serializable {
    private Integer numeroAgencia;
    private String cidade;
    private String estado;
    private Set<Conta> contas;


    public Agencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public Agencia(Integer numeroAgencia, String cidade, String estado) {
        this.numeroAgencia = numeroAgencia;
        this.cidade = cidade;
        this.estado = estado;
        this.contas = new HashSet<>();
    }

    private Agencia(Integer numeroAgencia, String cidade, String estado, Set<Conta> contas) {
        this.numeroAgencia = numeroAgencia;
        this.cidade = cidade;
        this.estado = estado;
        this.contas = contas;
    }

    public Agencia vinculaNovaConta(Conta conta){
        boolean resultado = this.contas.add(conta);
        if(!resultado) throw new IllegalStateException("Essa agencia já possui a conta " + conta);
        return new Agencia(this.numeroAgencia, this.cidade, this.estado, this.contas);
    }

    public Integer getNumeroAgencia(){
        return this.numeroAgencia;
    }
    public String getCidade(){
        return this.cidade;
    }
    public String getEstado(){
        return this.estado;
    }

    public Agencia atualizaCidade(String novaCidade){
        return new Agencia(this.numeroAgencia, novaCidade, this.estado, this.contas);
    }

    public boolean possuiNumeroAgencia(Integer numeroAgencia){
        return this.numeroAgencia.equals(numeroAgencia);
    }

    public boolean encontraseNaCidade(String cidade){
        return this.cidade.equals(cidade);
    }


    @Override
    public String toString() {
        return "Agencia{" +
                "numeroAgencia=" + numeroAgencia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agencia agencia = (Agencia) o;
        return numeroAgencia.equals(agencia.numeroAgencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroAgencia);
    }


}
