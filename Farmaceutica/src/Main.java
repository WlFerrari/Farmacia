import java.util.Scanner;

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
        int opcao = 0;
        do {
            System.out.println("1. Adicionar Funcionários");
            System.out.println("2. Remover Funcionários por ID");
            System.out.println("3. Exibir Funcionários");
            System.out.println("4. Exibir Funcionários por Setor");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> db.adicionarFuncionario(Funcionario.funcionarioPrompt(scanner));
                case 2 -> db.removerFuncionario(Funcionario.funcionarioIdPrompt(scanner));
                case 3 -> db.listarFuncionarios();
                case 4 -> db.listarFuncionarioPorSetor(Funcionario.funcionarioSetorPrompt(scanner));
                case 5 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuProdutos() {
        System.out.println("\n--- Gerenciamento de Produtos ---");
        // Implemente aqui: Adicionar, Listar, Atualizar estoque e preços
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
