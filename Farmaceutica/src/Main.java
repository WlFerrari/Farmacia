import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static BancoDeDados db = BancoDeDados.getInstanciaBanco();

    public static void main(String[] args) {
        inicializarCaixa();

        int opcao;
        do {
            System.out.println("\n====== MENU PRINCIPAL - SISTEMA FARMACÊUTICO ======");
            System.out.println("1. Gerenciar Funcionários");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Gerenciar Transportadoras");
            System.out.println("4. Gerenciar Negócios");
            System.out.println("5. Relatórios Financeiros");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> menuFuncionarios();
                case 2 -> menuProdutos();
                case 3 -> menuTransportadoras();
                case 4 -> menuNegocios();
                case 5 -> menuRelatorios();
                case 6 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void inicializarCaixa() {
        if (db.getCaixa() == null) {
            db.setCaixa(new Caixa(200000)); // valor inicial do enunciado
        }
    }

    private static void menuFuncionarios() {
        System.out.println("\n--- Gerenciamento de Funcionários ---");
        // Implemente aqui: Adicionar, Listar, Atualizar e Remover Funcionários
    }

    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Produtos ---");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Remover Produto");
            System.out.println("3. Atualizar Estoque");
            System.out.println("4. Atualizar Preço de Venda");
            System.out.println("5. Listar Produtos");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();

                    System.out.print("Valor de compra: ");
                    double valorCompra = scanner.nextDouble();

                    System.out.print("Valor de venda: ");
                    double valorVenda = scanner.nextDouble();

                    System.out.print("Quantidade em estoque: ");
                    int estoque = scanner.nextInt();
                    scanner.nextLine();

                    Produto novoProduto = new Produto(nome, valorCompra, valorVenda, estoque);
                    db.adicionarProduto(novoProduto);
                    System.out.println("Produto adicionado com sucesso!");
                }
                case 2 -> {
                    db.listarProdutos();
                    System.out.print("Digite o ID do produto a remover: ");
                    try {
                        int id = scanner.nextInt();
                        if (db.removerProdutoPorId(id)) {
                            System.out.println("Produto removido com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido.");
                    }
                }
                case 3 -> {
                    db.listarProdutos();
                    System.out.print("Digite o ID do produto para atualizar estoque: ");
                    try {
                        int id = scanner.nextInt();
                        System.out.print("Novo estoque: ");
                        int novoEstoque = scanner.nextInt();
                        scanner.nextLine();
                        if (db.atualizarEstoqueProduto(id, novoEstoque)) {
                            System.out.println("Estoque atualizado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido.");
                    }
                }
                case 4 -> {
                    db.listarProdutos();
                    System.out.print("Digite o ID do produto para atualizar preço: ");
                    try {
                        int id = scanner.nextInt();
                        System.out.print("Novo preço de venda: ");
                        double novoPreco = scanner.nextDouble();
                        scanner.nextLine();
                        if (db.atualizarPrecoProduto(id, novoPreco)) {
                            System.out.println("Preço atualizado com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("ID inválido.");
                    }
                }
                case 5 -> db.listarProdutos();
                case 6 -> System.out.println("Retornando ao menu principal.");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }


    private static void menuTransportadoras() {
        System.out.println("\n--- Gerenciamento de Transportadoras ---");
        // Implemente aqui: Cadastrar, listar e editar transportadoras
    }

    private static void menuNegocios() {
        System.out.println("\n--- Negócios em Andamento ---");
        // Implemente aqui: Criar venda/compra, listar negócios por status

    }

    private static void menuRelatorios() {
        Caixa caixa = db.getCaixa();
        double lucroMensal = caixa.calcularEstimativaLucro();
        double lucroAnual = lucroMensal * 12;

        System.out.println("\n--- Relatórios Financeiros ---");
        System.out.printf("Valor atual em caixa: R$ %.2f%n", caixa.getValor());
        System.out.printf("Estimativa de lucro mensal: R$ %.2f%n", lucroMensal);
        System.out.printf("Estimativa de lucro anual: R$ %.2f%n", lucroAnual);
    }
}
