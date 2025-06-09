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
        //variavel soma representa o somatorio do valor de venda dos produtos
        //variavel valorCompra representa o somatore do valor de compra dos produtos
        double soma = 0;
        double valorCompra = 0;
        for (Produto produto : db.getProdutos()) {
            soma = soma + (produto.getValorVenda() * produto.getQuantidadeEstoque());
            valorCompra = valorCompra + (produto.getValorCompra()) * produto.getQuantidadeEstoque();
        }
        return soma - valorCompra;
    }

    public double estimativaLucroAnual() {
        return calcularEstimativaLucro() * 12;
    }

    public void printEstimativaLucro() {
        System.out.println("Estimativa de lucro: " + calcularEstimativaLucro());
    }
}
