import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BancoDeDados db = BancoDeDados.getInstanciaBanco();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuFuncionarios();
                    break;
                case 2:
                    menuProdutos();
                    break;
                case 3:
                    menuTransportadoras();
                    break;
                case 4:
                    menuNegocios();
                    break;
                case 5:
                    menuRelatorios();
                    break;
                case 6:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n====== MENU PRINCIPAL - SISTEMA FARMACÊUTICO ======");
        System.out.println("1. Gerenciar Funcionários");
        System.out.println("2. Gerenciar Produtos");
        System.out.println("3. Gerenciar Transportadoras");
        System.out.println("4. Gerenciar Negócios");
        System.out.println("5. Relatórios Financeiros");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Funcionários ---");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Remover Funcionário por ID");
            System.out.println("3. Listar todos os Funcionários");
            System.out.println("4. Listar Funcionários por Setor");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    db.adicionarFuncionario(Funcionario.funcionarioPrompt(scanner));
                    break;
                case 2:
                    System.out.print("Digite o ID do funcionário a remover: ");
                    db.removerFuncionario(lerOpcao());
                    break;
                case 3:
                    db.listarFuncionarios();
                    break;
                case 4:
                    db.listarFuncionarioPorSetor(Funcionario.funcionarioSetorPrompt(scanner));
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Produtos ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Remover Produto por ID");
            System.out.println("3. Atualizar Estoque de Produto");
            System.out.println("4. Atualizar Preço de Venda de Produto");
            System.out.println("5. Listar Produtos");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    atualizarEstoqueProduto();
                    break;
                case 4:
                    atualizarPrecoProduto();
                    break;
                case 5:
                    db.listarProdutos();
                    break;
                case 6:
                    System.out.println("Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void menuTransportadoras() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Transportadoras ---");
            System.out.println("1. Cadastrar Transportadora");
            System.out.println("2. Listar Transportadoras");
            System.out.println("3. Editar Transportadora");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    // ATUALIZADO: Passando o scanner para o método prompt
                    db.adicionarTransportadora(Transportadora.transportadoraPrompt(scanner));
                    System.out.println("Transportadora cadastrada com sucesso!");
                    break;
                case 2:
                    db.listarTransportadoras();
                    break;
                case 3:
                    // ATUALIZADO: Passando o scanner para o método de edição
                    Transportadora.editarTransportadora(db.getTransportadoras(), scanner);
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void menuNegocios() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Negócios ---");
            System.out.println("1. Criar Negócio");
            System.out.println("2. Listar todos os Negócios");
            System.out.println("3. Listar Negócios por Status");
            System.out.println("4. Atualizar Status do Negócio");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    // ATUALIZADO: Passando o scanner para o método prompt
                    Negocio novoNegocio = Negocio.negocioPrompt(scanner);
                    if (novoNegocio != null) {
                        db.adicionarNegocio(novoNegocio);
                        System.out.println("\n>> Negócio criado com sucesso! <<");
                    } else {
                        System.out.println("\n>> Criação de negócio cancelada. <<");
                    }
                    break;
                case 2:
                    db.listarNegocios();
                    break;
                case 3:
                    System.out.print("Informe o status a consultar (ABERTO, CONCLUIDO, CANCELADO): ");
                    String statusStr = scanner.nextLine().toUpperCase();
                    try {
                        Status status = Status.valueOf(statusStr);
                        db.listarNegociosPorStatus(status);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Status inválido!");
                    }
                    break;
                case 4:
                    db.listarNegociosPorStatus(Status.ABERTO);
                    System.out.println("Digite o ID do Negócio: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.println("Escolha uma opção de Status: ");
                    System.out.println("1. CONCLUIDO");
                    System.out.println("2. CANCELADO");
                    System.out.print("Escolha: ");
                    opcao = lerOpcao();

                    if (opcao == 1) {
                        db.atualizarStatusNegocio(id, Status.CONCLUIDO);
                        System.out.println("Status atualizado com sucesso!");
                    } else if (opcao == 2) {
                        db.atualizarStatusNegocio(id, Status.CANCELADO);
                        System.out.println("Status atualizado com sucesso!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuRelatorios() {
        Caixa caixa = db.getCaixa();
        double lucroMensal = caixa.calcularEstimativaLucro();
        double lucroAnual = caixa.estimativaLucroAnual();

        System.out.println("\n--- Relatórios Financeiros ---");
        System.out.printf("Valor atual em caixa: R$ %.2f%n", caixa.getValor());
        System.out.printf("Estimativa de lucro mensal (baseado no estoque atual): R$ %.2f%n", lucroMensal);
        System.out.printf("Estimativa de lucro anual (baseado no estoque atual): R$ %.2f%n", lucroAnual);
    }

    // Métodos auxiliares para o menu de produtos
    private static void adicionarProduto() {
        try {
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            System.out.print("Valor de compra: ");
            double valorCompra = Double.parseDouble(scanner.nextLine());
            System.out.print("Valor de venda: ");
            double valorVenda = Double.parseDouble(scanner.nextLine());
            System.out.print("Quantidade em estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine());

            Produto novoProduto = new Produto(nome, valorCompra, valorVenda, estoque);
            db.adicionarProduto(novoProduto);
            System.out.println("Produto adicionado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Por favor, insira números onde for solicitado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerProduto() {
        db.listarProdutos();
        System.out.print("Digite o ID do produto a remover: ");
        try {
            int id = lerOpcao();
            if (db.removerProduto(id)) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido.");
        }
    }

    private static void atualizarEstoqueProduto() {
        db.listarProdutos();
        System.out.print("Digite o ID do produto para atualizar estoque: ");
        try {
            int id = lerOpcao();
            System.out.print("Novo estoque: ");
            int novoEstoque = lerOpcao();
            if (db.atualizarEstoqueProduto(id, novoEstoque)) {
                System.out.println("Estoque atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Entrada inválida.");
        }
    }

    private static void atualizarPrecoProduto() {
        db.listarProdutos();
        System.out.print("Digite o ID do produto para atualizar preço: ");
        try {
            int id = lerOpcao();
            System.out.print("Novo preço de venda: ");
            double novoPreco = Double.parseDouble(scanner.nextLine());
            if (db.atualizarPrecoProduto(id, novoPreco)) {
                System.out.println("Preço atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido.");
        }
    }

    // Método auxiliar para ler opção do usuário e limpar o buffer
    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); // descarta a entrada inválida
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer do scanner
        return opcao;
    }
}