package midias.midia;

import java.io.Serializable;

public class Episodio implements Serializable {
    private String nomeEp;
    private int duracaoEp;

    public Episodio(String nomeEp, int duracaoEp) {
        this.nomeEp = nomeEp;
        this.duracaoEp = duracaoEp;
    }
    public String getNomeEp() {
        return this.nomeEp;
    }
    public void setNomeEp(String nomeEp) {
        this.nomeEp = nomeEp;
    }
    public int getDuracaoEp() {
        return this.duracaoEp;
    }
    public void setDuracaoEp(int duracaoEp) {
        this.duracaoEp = duracaoEp;
    }
}
