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

        for(Negocio n : db.buscarNegociosPorStatus(Status.ABERTO)) {
            if(n.getTipo().equals(Tipo.VENDA)){
                for(ItemNegocio item : n.getItemsNegocio()){
                    valorTotalVenda += item.getProduto().getValorVenda() * item.getQuantidade();
                    valorTotalCompra += item.getProduto().getValorCompra() * item.getQuantidade();
                }
            }
        }

        return valorTotalVenda - valorTotalCompra;
    }

    public double estimativaLucroAnual() {
        return calcularEstimativaLucro() * 12;
    }
}