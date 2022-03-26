package back_end.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente implements Serializable {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;
    private String cidade;
    private String estado;
    private Set<Conta> contas;
    private Set<Emprestimo> emprestimosRealizados;
    private Funcionario gerente;

    public Cliente(String nome, String cpf, LocalDate dataNascimento,
                   String endereco, String cidade, String estado, Funcionario gerente) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.contas = new HashSet<>();
        this.emprestimosRealizados = new HashSet<>();
        this.gerente = gerente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void atualizaId(Long id){
        this.id = id;
    }

    public void atribuiConta(Conta conta){
        boolean jaFoiAtribuida = this.contas.add(conta);
        //if(jaFoiAtribuida) throw new IllegalStateException("Essa conta já foi atribuida a esse cliente");
    }

    public void atribuiGerente(Funcionario gerente){
        this.gerente = gerente;
    }

    public Funcionario getGerente(){
        return this.gerente;
    }

    public Integer getNumeroFuncionalGerente(){
        return this.gerente.getNumeroFuncional();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
