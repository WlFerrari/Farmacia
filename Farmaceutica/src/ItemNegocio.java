public class ItemNegocio {
    private Produto produto;
    private int quantidade;

    public ItemNegocio(Produto produto, int quantidade) {
        this.produto = produto;

        if(quantidade<=produto.getQuantidadeEstoque()){
            this.quantidade = quantidade;
        }else{
            System.out.println("Erro - existem apenas "+produto.getQuantidadeEstoque()+" unidades deste produto em estoque.");
        }

    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade<=produto.getQuantidadeEstoque()){
            this.quantidade = quantidade;
        }else{
            System.out.println("Erro - existem apenas "+produto.getQuantidadeEstoque()+" unidades deste produto em estoque.");
        }
    }

    @Override
    public String toString() {
        return String.format("Produto: %s | Quantidade: %d",
                produto.getNome(), quantidade);
    }
}
