import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Funcionario {
    private static int counter = 0;
    private final int id;
    private String nome;
    private int idade;
    private Genero genero;
    private Setor setor;
    private double salariobase;

    // Benefícios
    private double va = 300;
    private double vr = 300;
    private double vt = 200;
    private double planoSaude = 3000;
    private double planoOdontologico = 3000;

    public Funcionario(String nome, int idade, Genero genero, Setor setor, double salariobase) {
        this.id = ++counter;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.setor = setor;
        this.salariobase = salariobase;
        ajustarBeneficiosPorSetor();
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
    public Setor getSetor() { return setor; }
    public void setSetor(Setor setor) { this.setor = setor; }
    public double getSalariobase() { return salariobase; }
    public void setSalariobase(double salariobase) { this.salariobase = salariobase; }

    // Métodos de Lógica de Negócio
    private void ajustarBeneficiosPorSetor() {
        switch (this.setor) {
            case ATENDIMENTO_AO_CLIENTE:
            case VENDAS:
                this.planoOdontologico -= 1000;
                break;
            case FINANCEIRO:
            case ALMOXARIFADO:
            case GESTAO_DE_PESSOAS:
                this.va += 200;
                this.vr += 200;
                this.planoSaude += 500;
                this.planoOdontologico -= 500;
                break;
            case GERENCIA:
                this.va += 700;
                this.vr += 700;
                this.planoSaude += 1200;
                break;
            default:
                // Nenhum benefício adicional para outros setores
                break;
        }
    }

    public double calcularValorIR() {
        if (salariobase <= 2428.80) return 0.0;
        if (salariobase <= 2826.65) return (salariobase * 0.075) - 182.16;
        if (salariobase <= 3751.05) return (salariobase * 0.15) - 394.16;
        if (salariobase <= 4664.68) return (salariobase * 0.225) - 675.49;
        return (salariobase * 0.275) - 908.75;
    }

    public double getSalarioLiquido() {
        return salariobase - calcularValorIR();
    }

    public double calcularParticipacaoLucros() {
        BancoDeDados bd = BancoDeDados.getInstanciaBanco();
        if (bd.getFuncionarios().isEmpty()) return 0;
        return bd.getCaixa().calcularEstimativaLucro() * 0.1 / bd.getFuncionarios().size();
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d\nNome: %s\nIdade: %d\nGênero: %s\nSetor: %s\nSalário Base: R$ %.2f\n" +
                        "Salário Líquido (est.): R$ %.2f\nImposto de Renda (est.): R$ %.2f\n" +
                        "Benefícios: VA(R$%.2f), VR(R$%.2f), VT(R$%.2f), Saúde(R$%.2f), Odonto(R$%.2f)\n" +
                        "Part. Lucros (est.): R$ %.2f",
                id, nome, idade, genero, setor, salariobase, getSalarioLiquido(), calcularValorIR(),
                va, vr, vt, planoSaude, planoOdontologico, calcularParticipacaoLucros()
        );
    }

    // Métodos Estáticos para Entrada do Usuário (Prompt)
    public static Funcionario funcionarioPrompt(Scanner in) {
        try {
            System.out.print("Digite o nome do funcionário: ");
            String nome = in.nextLine();

            System.out.print("Digite a idade: ");
            int idade = Integer.parseInt(in.nextLine());

            System.out.println("Selecione o gênero: 1.MASCULINO, 2.FEMININO, 3.NAO_INFORMADO");
            int genOpt = Integer.parseInt(in.nextLine());
            Genero genero = Genero.values()[genOpt - 1];

            System.out.println("Selecione o setor: 1.GERENCIA, 2.ATENDIMENTO_AO_CLIENTE, 3.GESTAO_DE_PESSOAS, 4.FINANCEIRO, 5.VENDAS, 6.ALMOXARIFADO");
            int setorOpt = Integer.parseInt(in.nextLine());
            Setor setor = Setor.values()[setorOpt - 1];

            System.out.print("Digite o salário base: ");
            double salariobase = Double.parseDouble(in.nextLine());

            return new Funcionario(nome, idade, genero, setor, salariobase);
        } catch (Exception e) {
            System.out.println("Erro ao criar funcionário. Dados inválidos. " + e.getMessage());
            return null;
        }
    }

    public static Setor funcionarioSetorPrompt(Scanner in) {
        try {
            System.out.print("Digite o Setor do funcionário (ex: GERENCIA, VENDAS): ");
            String setorStr = in.nextLine().trim().toUpperCase();
            setorStr = setorStr.replace(" ", "_");
            return Setor.valueOf(setorStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Setor inválido. Tente novamente.");
            return null;
        }
    }
}