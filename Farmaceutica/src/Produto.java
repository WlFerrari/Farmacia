public class Produto {
    private static int contadorId = 0;
    private final int id;
    private String nome;
    private double valorCompra;
    private double valorVenda;
    private int quantidadeEstoque;
    private boolean isAtivo;

    public Produto(String nome, double valorCompra, double valorVenda, int quantidadeEstoque) {
        if (valorCompra < 0 || valorVenda < 0 || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Valores de produto não podem ser negativos.");
        }
        this.id = ++contadorId;
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        isAtivo = true;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Produto: %s | Compra: R$%.2f | Venda: R$%.2f | Estoque: %d",
                id, nome, valorCompra, valorVenda, quantidadeEstoque);
    }
}