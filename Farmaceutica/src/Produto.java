public class Produto {
    private static int contadorId = 1; // Gerador de ID incremental
    private int id;
    private String nome;
    private double valorCompra;
    private double valorVenda;
    private int quantidadeEstoque;

    public Produto(String nome, double valorCompra, double valorVenda, int quantidadeEstoque) {
        this.id = contadorId++;
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;

        if (valorCompra < 0 || valorVenda < 0 || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Valores de produto não podem ser negativos.");
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


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

    @Override
    public String toString() {
        return "Produto: " + nome +
                "\nCompra: R$" + valorCompra +
                ", Venda: R$" + valorVenda +
                ", Estoque: " + quantidadeEstoque +
                ", ID: " + id;
    }
}




