import java.util.List;

public class Transportadora {
    private String nome;
    List<String> regioes;

    public Transportadora(String nome, List<String> regioes) {
        this.nome = nome;
        this.regioes = regioes;
    }

    @Override
    public String toString() {
        return nome + " - Regiões: " + String.join(", ", regioes);
    }
}
