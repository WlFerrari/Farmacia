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
                + "\nGestao de Pessoas: " + counterGestao
                + "\nFinanceiro: " + counterFinanceiro
                + "\nVendas: " + counterVendas
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

    public ArrayList<Negocio> buscarNegociosPorStatus(Status status) {
        ArrayList<Negocio> negociosAbertos = new ArrayList<>();
        for (Negocio n : negocios) {
            if (n.getStatus() == status) {
                negociosAbertos.add(n);
            }
        }
        return negociosAbertos;
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
        Funcionario carlos = new Funcionario("Carlos Alberto", 50, Genero.MASCULINO, Setor.GERENCIA, 5000.00);

        Funcionario joao = new Funcionario("João Gouveia", 20, Genero.MASCULINO, Setor.ATENDIMENTO_AO_CLIENTE, 2000.00);
        Funcionario maria = new Funcionario("Maria Souza", 28, Genero.FEMININO, Setor.ATENDIMENTO_AO_CLIENTE, 2000.00);
        Funcionario carlosPereira = new Funcionario("Carlos Pereira", 35, Genero.MASCULINO, Setor.ATENDIMENTO_AO_CLIENTE, 2000.00);
        Funcionario ana = new Funcionario("Ana Lima", 26, Genero.FEMININO, Setor.ATENDIMENTO_AO_CLIENTE, 2000.00);

        Funcionario lucas = new Funcionario("Lucas Almeida", 40, Genero.MASCULINO, Setor.GESTAO_DE_PESSOAS, 3000.00);
        Funcionario isabela = new Funcionario("Isabela Fernandes", 32, Genero.FEMININO, Setor.GESTAO_DE_PESSOAS, 3000.00);
        Funcionario rafael = new Funcionario("Rafael Costa", 29, Genero.MASCULINO, Setor.GESTAO_DE_PESSOAS, 3000.00);
        Funcionario carolina = new Funcionario("Carolina Rocha", 38, Genero.FEMININO, Setor.GESTAO_DE_PESSOAS, 3000.00);

        Funcionario mariaDeLourdes = new Funcionario("Maria de Lourdes", 20, Genero.FEMININO, Setor.FINANCEIRO, 4000.00);
        Funcionario paulo = new Funcionario("Paulo Martins", 33, Genero.MASCULINO, Setor.FINANCEIRO, 4000.00);
        Funcionario renata = new Funcionario("Renata Gomes", 29, Genero.FEMININO, Setor.FINANCEIRO, 4000.00);

        Funcionario bruno = new Funcionario("Bruno Silva", 27, Genero.MASCULINO, Setor.VENDAS, 2800.00);
        Funcionario juliana = new Funcionario("Juliana Costa", 31, Genero.FEMININO, Setor.VENDAS, 2800.00);
        Funcionario felipe = new Funcionario("Felipe Oliveira", 24, Genero.MASCULINO, Setor.VENDAS, 2800.00);
        Funcionario carla = new Funcionario("Carla Mendes", 29, Genero.FEMININO, Setor.VENDAS, 2800.00);
        Funcionario diego = new Funcionario("Diego Ramos", 35, Genero.MASCULINO, Setor.VENDAS, 2800.00);

        Funcionario paula = new Funcionario("Paula Andrade", 34, Genero.FEMININO, Setor.ALMOXARIFADO, 2200.00);
        Funcionario marcelo = new Funcionario("Marcelo Souza", 41, Genero.MASCULINO, Setor.ALMOXARIFADO, 2200.00);
        Funcionario fernanda = new Funcionario("Fernanda Lima", 28, Genero.FEMININO, Setor.ALMOXARIFADO, 2000.00);


        //Inclusão no banco de dados
        this.adicionarFuncionario(carlos);
        this.adicionarFuncionario(joao);
        this.adicionarFuncionario(maria);
        this.adicionarFuncionario(carlosPereira);
        this.adicionarFuncionario(ana);
        this.adicionarFuncionario(lucas);
        this.adicionarFuncionario(isabela);
        this.adicionarFuncionario(rafael);
        this.adicionarFuncionario(carolina);
        this.adicionarFuncionario(mariaDeLourdes);
        this.adicionarFuncionario(paulo);
        this.adicionarFuncionario(renata);
        this.adicionarFuncionario(bruno);
        this.adicionarFuncionario(juliana);
        this.adicionarFuncionario(felipe);
        this.adicionarFuncionario(carla);
        this.adicionarFuncionario(diego);
        this.adicionarFuncionario(paula);
        this.adicionarFuncionario(marcelo);
        this.adicionarFuncionario(fernanda);


        //Items para a criação de um negócio
        ItemNegocio item1 = new ItemNegocio(produtos.get(0), 35);
        ItemNegocio item2 = new ItemNegocio(produtos.get(1), 50);
        List<ItemNegocio> itemsCompra = new ArrayList<>(List.of(item1, item2));

        ItemNegocio item3 = new ItemNegocio(produtos.get(1), 80);
        ItemNegocio item4 = new ItemNegocio(produtos.get(5), 80);
        List<ItemNegocio> itemsVenda = new ArrayList<>(List.of(item3, item4));

        // Transportadoras
        List<String> regioes1 = new ArrayList<>(List.of("Sul", "Sudeste"));
        List<String> regioes2 = new ArrayList<>(List.of("Norte", "Centro-Oeste"));
        List<String> regioes3 = new ArrayList<>(List.of("Nordeste"));
        Transportadora transportadoraX = new Transportadora("Transportadora 1", regioes1);
        transportadoras.add(transportadoraX);

        Transportadora transportadoraY = new Transportadora("Transportadora 2", regioes2);
        transportadoras.add(transportadoraY);

        Transportadora transportadoraZ = new Transportadora("Transportadora 3", regioes3);
        transportadoras.add(transportadoraZ);

        // Negócios
        List<Funcionario> participantesCompra = new ArrayList<>(List.of(joao));
        List<Funcionario> participantesVenda = new ArrayList<>(List.of(bruno, juliana));
        Negocio negocioInicialCompra = new Negocio(Tipo.COMPRA, Status.ABERTO, itemsCompra, participantesCompra, transportadoraX);
        negocios.add(negocioInicialCompra);
        Negocio negocioInicialVenda = new Negocio(Tipo.VENDA, Status.ABERTO, itemsVenda, participantesVenda, transportadoraX);
        negocios.add(negocioInicialVenda);
    }
}