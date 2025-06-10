import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa o padrão Singleton para servir como um banco de dados em memória.
 * Garante que exista apenas uma instância para gerenciar todas as coleções de dados da aplicação.
 */
public class BancoDeDados {

    // --- Singleton Implementation ---
    private static BancoDeDados instancia;

    // Método público estático para obter a instância única da classe.
    public static BancoDeDados getInstanciaBanco() {
        if (instancia == null) {
            instancia = new BancoDeDados();
        }
        return instancia;
    }

    // --- Attributes ---
    private final Caixa caixa;
    private final ArrayList<Funcionario> funcionarios;
    private final ArrayList<Produto> produtos;
    private final ArrayList<Transportadora> transportadoras;
    private final ArrayList<Negocio> negocios;

    // --- Constructor (Private to enforce Singleton) ---
    private BancoDeDados() {
        this.funcionarios = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.negocios = new ArrayList<>();
        this.caixa = new Caixa(200000); // Valor inicial do caixa

        popularDadosIniciais();
    }

    // --- Getters ---
    public ArrayList<Funcionario> getFuncionarios() { return funcionarios; }
    public ArrayList<Produto> getProdutos() { return produtos; }
    public ArrayList<Transportadora> getTransportadoras() { return transportadoras; }
    public ArrayList<Negocio> getNegocios() { return negocios; }
    public Caixa getCaixa() { return caixa; }


    // --- Add Methods (Create) ---
    public void adicionarFuncionario(Funcionario funcionario) {
        if (funcionario != null) funcionarios.add(funcionario);
    }
    public void adicionarProduto(Produto produto) {
        if (produto != null) produtos.add(produto);
    }
    public void adicionarTransportadora(Transportadora transportadora) {
        if (transportadora != null) transportadoras.add(transportadora);
    }
    public void adicionarNegocio(Negocio negocio) {
        if (negocio != null) negocios.add(negocio);
    }

    // --- Update Methods ---
    public boolean atualizarEstoqueProduto(int id, int novoEstoque) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produto.setQuantidadeEstoque(novoEstoque);
            return true;
        }
        return false;
    }

    public boolean atualizarPrecoProduto(int id, double novoPreco) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produto.setValorVenda(novoPreco);
            return true;
        }
        return false;
    }

    // ... outros métodos de atualização ...

    // --- Remove Methods (Delete) ---
    public void removerFuncionario(int id) {
        funcionarios.removeIf(f -> f.getId() == id);
    }
    public boolean removerProduto(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
    public void removerNegocioPorId(int id) {
        negocios.removeIf(n -> n.getId() == id);
    }
    public void removerTransportadoraPorNome(String nome) {
        transportadoras.removeIf(t -> t.getNome().equalsIgnoreCase(nome));
    }

    // --- Search Methods (Read) ---
    public Funcionario getFuncionarioPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }
    public Produto buscarProdutoPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    public Negocio buscarNegocioPorId(int id) {
        return negocios.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }
    public Transportadora buscarTransportadoraPorNome(String nome) {
        return transportadoras.stream().filter(t -> t.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    // --- Listing Methods (Console Output) ---
    public void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println(f.toString());
            System.out.println("------------------");
        }
    }

    public void listarFuncionariosResumido() {
        System.out.println("\n--- Funcionários Cadastrados ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.printf("ID: %d | Nome: %s | Setor: %s%n", f.getId(), f.getNome(), f.getSetor().name());
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.printf("%-5s | %-30s | %-12s | %-12s | %-10s%n", "ID", "Nome", "V. Compra", "V. Venda", "Estoque");
        System.out.println("-----------------------------------------------------------------------------");
        for (Produto p : produtos) {
            System.out.printf("%-5d | %-30s | R$ %-10.2f | R$ %-10.2f | %-10d%n",
                    p.getId(), p.getNome(), p.getValorCompra(), p.getValorVenda(), p.getQuantidadeEstoque());
        }
    }

    public void listarTransportadoras() {
        System.out.println("\n--- Lista de Transportadoras ---");
        if (transportadoras.isEmpty()) {
            System.out.println("Nenhuma transportadora cadastrada.");
            return;
        }
        for (Transportadora t : transportadoras) {
            System.out.println(t.toString());
            System.out.println("------------------");
        }
    }

    public void listarNegocios() {
        System.out.println("\n--- Lista de Negócios ---");
        if (negocios.isEmpty()) {
            System.out.println("Nenhum negócio registrado.");
            return;
        }
        for (Negocio n : negocios) {
            System.out.println(n.toString());
            System.out.println("------------------");
        }
    }

    public void listarFuncionarioPorSetor(Setor setor) {
        System.out.printf("\n--- Funcionários do Setor: %s ---%n", setor.name());
        long count = funcionarios.stream()
                .filter(f -> f.getSetor() == setor)
                .peek(f -> {
                    System.out.println(f.toString());
                    System.out.println("------------------");
                })
                .count();
        if (count == 0) {
            System.out.println("Nenhum funcionário encontrado neste setor.");
        }
    }

    public void listarNegociosPorStatus(Status status) {
        System.out.printf("%n--- Negócios com Status: %s ---%n", status.name());
        long count = negocios.stream()
                .filter(n -> n.getStatus() == status)
                .peek(n -> {
                    System.out.println(n.toString());
                    System.out.println("------------------");
                })
                .count();
        if (count == 0) {
            System.out.println("Nenhum negócio encontrado com este status.");
        }
    }

    // --- Data Seeding ---
    private void popularDadosIniciais() {
        // Produtos
        produtos.add(new Produto("Dipirona 500mg", 1.50, 3.00, 100));
        produtos.add(new Produto("Paracetamol 750mg", 1.80, 3.50, 120));
        produtos.add(new Produto("Ibuprofeno 400mg", 2.20, 4.50, 80));
        produtos.add(new Produto("Amoxicilina 500mg", 3.00, 6.00, 60));
        // ... (restante dos produtos)

        // Funcionários
        Funcionario joao = new Funcionario("João", 20, Genero.MASCULINO, Setor.ATENDIMENTO_AO_CLIENTE, 1000.00);
        Funcionario maria = new Funcionario("Maria", 20, Genero.FEMININO, Setor.FINANCEIRO, 1200.00);
        Funcionario matheus = new Funcionario("Matheus", 20, Genero.NAO_INFORMADO, Setor.GERENCIA, 5000.00);
        // ... (restante dos funcionários)
        funcionarios.add(joao);
        funcionarios.add(maria);
        funcionarios.add(matheus);

        // Transportadoras
        List<String> regioes = new ArrayList<>(List.of("Sul", "Sudeste"));
        Transportadora transportadoraX = new Transportadora("Transportadora X", regioes);
        transportadoras.add(transportadoraX);

        // Negócios
        List<Funcionario> participantes = new ArrayList<>(List.of(joao));
        Negocio negocioInicial = new Negocio(Tipo.COMPRA, Status.CONCLUIDO, participantes, transportadoraX);
        negocios.add(negocioInicial);
    }
}