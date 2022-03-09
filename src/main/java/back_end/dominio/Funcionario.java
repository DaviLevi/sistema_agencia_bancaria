package back_end.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class Funcionario {
    private Integer numeroFuncional; // natural
    private String nome;
    private String telefone;
    private Set<Dependente> dependentes;
    private Funcionario supervisor;
    private LocalDate dataAdmissao;
    private Agencia agencia;

    public Funcionario(Integer numeroFuncional){
        this.numeroFuncional = numeroFuncional;
    }

    public Funcionario(Integer numeroFuncional, String nome, String telefone, Set<Dependente> dependentes,
                       Funcionario supervisor, LocalDate dataAdmissao, Agencia agencia) {
        this.numeroFuncional = numeroFuncional;
        this.nome = nome;
        this.telefone = telefone;
        this.dependentes = dependentes;
        this.supervisor = supervisor;
        this.dataAdmissao = dataAdmissao;
        this.agencia = agencia;
    }

    public boolean ehGerente(){ return supervisor == null; }

    public Period calculaTempoDeServico(){
        return Period.between(this.dataAdmissao, LocalDate.now());
    }

    public Integer getNumeroFuncional() {
        return this.numeroFuncional;
    }

    public void atualizaAgencia(Agencia agencia){
        this.agencia = agencia;
    }

    public Agencia getAgencia(){
        return this.agencia;
    }

    public Integer getNumeroAgencia(){
        return this.agencia.getNumeroAgencia();
    }

    public Integer getNumeroFuncionalDoSupervisor() {
        return this.supervisor.numeroFuncional;
    }
}
