package back_end.dominio;

import java.io.Serializable;

public class Dependente implements Serializable {
    private String nome;

    public Dependente(String nome) {
        this.nome = nome;
    }
}
