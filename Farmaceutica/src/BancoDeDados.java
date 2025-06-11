import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa o padrão Singleton para servir como um banco de dados em memória.
 * Garante que exista apenas uma instância para gerenciar todas as coleções de dados da aplicação.
 */
public class BancoDeDados {

    // --- Singleton Implementation ---
    private static BancoDeDados instancia;
    // --- Constantes ---
    private final int LIMITE_GERENCIA = 1;
    private final int LIMITE_ATENDIMENTO = 4;
    private final int LIMITE_GESTAO = 4;
    private final int LIMITE_FINANCEIRO = 3;
    private final int LIMITE_VENDAS = 5;
    private final int LIMITE_ALMOXARIFADO = 3;
    // --- Attributes ---
    private final Caixa caixa;
    private final ArrayList<Funcionario> funcionarios;
    private final ArrayList<Produto> produtos;
    private final ArrayList<Transportadora> transportadoras;
    private final ArrayList<Negocio> negocios;
    private int counterGerencia;
    private int counterAtendimento;
    private int counterGestao;
    private int counterFinanceiro;
    private int counterAlmoxarifado;
    private int counterVendas;

    // --- Constructor (Private to enforce Singleton) ---
    private BancoDeDados() {
        this.funcionarios = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.negocios = new ArrayList<>();
        this.caixa = new Caixa(200000); // Valor inicial do caixa
        counterGerencia = 0;
        counterAtendimento = 0;
        counterGestao = 0;
        counterFinanceiro = 0;
        counterVendas = 0;
        counterAlmoxarifado = 0;

        popularDadosIniciais();
    }

    // Método público estático para obter a instância única da classe.
    public static BancoDeDados getInstanciaBanco() {
        if (instancia == null) {
            instancia = new BancoDeDados();
        }
        return instancia;
    }

    // --- Getters ---
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public ArrayList<Negocio> getNegocios() {
        return negocios;
    }

    public Caixa getCaixa() {
        return caixa;
    }


    // --- Add Methods (Create) ---
    public void addValorCaixa(double valor){
        caixa.adicionarValor(valor);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        try {
            // checando se ainda ha vaga em lista funcionarios
            if (checarLimiteFuncionarios(funcionarios, funcionario)) {
                if (funcionario != null) {
                    switch (funcionario.getSetor()) {
                        case Setor.GERENCIA -> this.counterGerencia++;
                        case Setor.ATENDIMENTO_AO_CLIENTE -> this.counterAtendimento++;
                        case Setor.GESTAO_DE_PESSOAS -> this.counterGestao++;
                        case Setor.FINANCEIRO -> this.counterFinanceiro++;
                        case Setor.VENDAS -> this.counterVendas++;
                        case Setor.ALMOXARIFADO -> this.counterAlmoxarifado++;
                        default -> throw new RuntimeException("Setor invalido");
                    }
                    funcionarios.add(funcionario);
                }
            } else {
                throw new RuntimeException("Não foi possivel adicionar funcionario - Setor designado cheio!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para checar se o limite de funcionarios está cheio
    // Retorna falso se a lista de funcionarios estiver cheia, verdadeiro caso ainda haja vaga na lista
    public boolean checarLimiteFuncionarios(ArrayList<Funcionario> funcionarios, Funcionario func) {
        int innerCounterGerencia = this.counterGerencia;
        int innerCounterAlmoxarifado = this.counterAlmoxarifado;
        int innerCounterAtendimento = this.counterAtendimento;
        int innerCounterFinanceiro = this.counterFinanceiro;
        int innerCounterVendas = this.counterVendas;
        int innerCounterGestao = this.counterGestao;

        switch (func.getSetor()) {
            case Setor.GERENCIA -> innerCounterGerencia++;
            case Setor.ATENDIMENTO_AO_CLIENTE -> innerCounterAtendimento++;
            case Setor.GESTAO_DE_PESSOAS -> innerCounterGestao++;
            case Setor.FINANCEIRO -> innerCounterFinanceiro++;
            case Setor.VENDAS -> innerCounterVendas++;
            case Setor.ALMOXARIFADO -> innerCounterAlmoxarifado++;
            default -> throw new RuntimeException("Setor invalido");
        }

        // Checando se cada Setor está cheio
        if (innerCounterGerencia > LIMITE_GERENCIA) {
            return false;
        } else if (innerCounterAtendimento > LIMITE_ATENDIMENTO) {
            return false;
        } else if (innerCounterGestao > LIMITE_GESTAO) {
            return false;
        } else if (innerCounterFinanceiro > LIMITE_FINANCEIRO) {
            return false;
        } else if (innerCounterVendas > LIMITE_VENDAS) {
            return false;
        } else if (innerCounterAlmoxarifado > LIMITE_ALMOXARIFADO) {
            return false;
        } else {
            return true;
        }
    }

    // Exibe o contador de funcionarios em cada setor
    public void exibirContagemFuncionarios() {
        System.out.println();
        System.out.println("Gerencia: " + counterGerencia
                + "\nAtendimento: " + counterAtendimento
                + "\nGestao: " + counterGestao
                + "\nFinanceiro: " + counterFinanceiro
                + "\nAlmoxarifado: " + counterAlmoxarifado);
        System.out.println();
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

    public boolean atualizarStatusNegocio(int id, Status status) {
        Negocio negocio = buscarNegocioPorId(id);
        if (negocio != null) {
            negocio.atualizarStatus(id, status);
            return true;
        }
        return false;
    }

    // ... outros métodos de atualização ...

    // --- Remove Methods (Delete) ---
    public void removerFuncionario(int id) {
        //funcionarios.removeIf(f -> f.getId() == id);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionario.setAtivo(false);
                switch (funcionario.getSetor()) {
                    case Setor.GERENCIA -> counterGerencia--;
                    case Setor.ATENDIMENTO_AO_CLIENTE -> counterAtendimento--;
                    case Setor.GESTAO_DE_PESSOAS -> counterGestao--;
                    case Setor.FINANCEIRO -> counterFinanceiro--;
                    case Setor.VENDAS -> counterVendas--;
                    case Setor.ALMOXARIFADO -> counterAlmoxarifado--;
                    default -> throw new RuntimeException("Setor invalido");
                }
                return;
            }
        }
        System.out.println("\nFuncionario nao encontrado");
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

    public void removeValorCaixa(double valor){
        caixa.removerValor(valor);
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

    public Transportadora buscarTransportadoraPorId(int id) {
        return transportadoras.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // --- Listing Methods (Console Output) ---
    public void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                System.out.println(f.toString());
                System.out.println("------------------");
            }
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
                .filter(f -> f.getSetor() == setor && f.isAtivo())
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
        produtos.add(new Produto("Espironolactona 25mg", 2.50, 6.20, 35));
        produtos.add(new Produto("Omeprazol 20mg", 2.00, 4.00, 90));
        produtos.add(new Produto("Loratadina 10mg", 1.60, 3.20, 75));
        produtos.add(new Produto("Simeticona 125mg", 1.90, 3.80, 50));
        produtos.add(new Produto("Ácido Acetilsalicílico 100mg", 1.40, 2.80, 110));
        produtos.add(new Produto("Cloridrato de Sertralina 50mg", 4.00, 8.00, 30));
        produtos.add(new Produto("Metformina 850mg", 2.50, 5.00, 70));
        // ... (restante dos produtos)

        // Funcionários
        Funcionario joao = new Funcionario("João", 20, Genero.MASCULINO, Setor.ATENDIMENTO_AO_CLIENTE, 1000.00);
        Funcionario maria = new Funcionario("Maria", 20, Genero.FEMININO, Setor.FINANCEIRO, 1200.00);
        Funcionario matheus = new Funcionario("Matheus", 20, Genero.NAO_INFORMADO, Setor.GERENCIA, 5000.00);
        // ... (restante dos funcionários)
        this.adicionarFuncionario(joao);
        this.adicionarFuncionario(maria);
        this.adicionarFuncionario(matheus);

        //Items para a criação de um negócio
        ItemNegocio item1 = new ItemNegocio(produtos.get(0), 35);
        ItemNegocio item2 = new ItemNegocio(produtos.get(1), 50);
        List<ItemNegocio> items = new ArrayList<>(List.of(item1, item2));

        // Transportadoras
        List<String> regioes = new ArrayList<>(List.of("Sul", "Sudeste"));
        Transportadora transportadoraX = new Transportadora("Transportadora X", regioes);
        transportadoras.add(transportadoraX);

        // Negócios
        List<Funcionario> participantes = new ArrayList<>(List.of(joao));
        Negocio negocioInicial = new Negocio(Tipo.COMPRA, Status.CONCLUIDO, items, participantes, transportadoraX);
        negocios.add(negocioInicial);
    }
}