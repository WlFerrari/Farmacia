public class Caixa {
    private double valor;

    public Caixa(double valorInicial) {
        this.valor = valorInicial;
    }

    public double getValor() {
        return valor;
    }

    public void adicionarValor(double total) {
        if (total > 0) this.valor += total;
    }

    public void removerValor(double total) {
        if (total > 0) this.valor -= total;
    }

    public double calcularEstimativaLucro() {
        BancoDeDados db = BancoDeDados.getInstanciaBanco();
        double valorTotalVenda = 0;
        double valorTotalCompra = 0;

        for (Produto produto : db.getProdutos()) {
            valorTotalVenda += produto.getValorVenda() * produto.getQuantidadeEstoque();
            valorTotalCompra += produto.getValorCompra() * produto.getQuantidadeEstoque();
        }
        return valorTotalVenda - valorTotalCompra;
    }

    public double estimativaLucroAnual() {
        return calcularEstimativaLucro() * 12;
    }
}