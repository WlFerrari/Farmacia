import java.util.List;

public class Transportadora {
    private String nome;
    private List<String> regioes;

    public Transportadora(String nome, List<String> regioes) {
        this.nome = nome;
        this.regioes = regioes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRegioes() {
        return regioes;
    }

    public void setRegioes(List<String> regioes) {
        this.regioes = regioes;
    }

    @Override
    public String toString() {
        return "Nome da transportadora: "+nome + " \nRegiões de cobertura: " + String.join(", ", regioes);
    }
}
