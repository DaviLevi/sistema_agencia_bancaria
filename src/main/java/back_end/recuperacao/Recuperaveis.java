package back_end.recuperacao;

import java.util.List;

public class Recuperaveis implements Recuperavel{

    private List<Recuperavel> recuperaveis;

    public Recuperaveis(List<Recuperavel> recuperaveis){
        this.recuperaveis = recuperaveis;
    }

    @Override
    public void recuperaBackup() {
        for (Recuperavel recuperavel : recuperaveis) {
            recuperavel.recuperaBackup();
        }
    }

    @Override
    public void realizaBackup() {
        for (Recuperavel recuperavel : recuperaveis) {
            recuperavel.realizaBackup();
        }
    }
}
