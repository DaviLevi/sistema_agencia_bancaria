package back_end.dominio;

import front_end.Operacoes.Cupons;

import java.io.Serializable;
import java.time.LocalDate;

public class Cupom  implements Serializable {
    private Long id;
    private LocalDate validade;

    public Cupom(Long id, LocalDate validade){
        this.id = id;
        this.validade = validade;
    }
}
