import java.util.UUID;

public class Caixa {
    private double valor;

    public Caixa(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double calcularEstimativaLucro() {
        BancoDeDados db = BancoDeDados.getInstanciaBanco();
        double soma = 0;
        for (Produto produto : db.getProdutos()) {
            //TODO ITERAR EM NEGOCIOS
        }
        return 0.0;
    }
}
