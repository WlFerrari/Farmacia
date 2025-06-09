import java.util.Scanner;
import java.util.UUID;

public class  Funcionario {
    private static int counter = 0;
    private int id;
    private String nome;
    private int idade;
    private Genero genero;
    private Setor setor;
    private double salariobase;
    private double VA = 300;
    private double VR = 300;
    private double VT = 200;
    private double planoSaude = 3000;
    private double planoOdontologico = 3000;

    public Funcionario(String nome, int idade, Genero genero, Setor setor, double salariobase) {
        id = ++counter;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.setor = setor;
        this.salariobase = salariobase;

    }

    public static Funcionario funcionarioPrompt(Scanner in){
        System.out.print("Digite o nome do funcionário: ");
        String nome = in.nextLine();

        System.out.print("Digite a idade do funcionário: ");
        int idade = in.nextInt();
        in.nextLine();

        System.out.print("Digite o genero do funcionário:  ");
        Genero genero = Genero.valueOf(in.nextLine().trim().toUpperCase());

        System.out.print("Digite o setor do funcionário:  ");
        Setor setor = Setor.valueOf(in.nextLine().trim().toUpperCase());

        System.out.print("Digite o valor do salário base do funcionário: ");
        double salariobase = Double.valueOf(in.nextLine());

        return new Funcionario(nome, idade, genero, setor, salariobase);
    }

    public static int funcionarioIdPrompt(Scanner in){
        System.out.println("Digite o ID do funcionário: ");
        int id = Integer.parseInt(in.nextLine());
        return id;
    }

    public static Setor funcionarioSetorPrompt(Scanner in){
        System.out.println("Digite o Setor do funcionário: ");
        Setor s = Setor.valueOf(in.nextLine().trim().toUpperCase());
        return s;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public Setor getSetor() {
        return setor;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public double getVA() {
        return VA;
    }

    public double getVR() {
        return VR;
    }

    public double getVT() {
        return VT;
    }

    public double getPlanoSaude() {
        return planoSaude;
    }

    public double getPlanoOdontologico() {
        return planoOdontologico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public void setVA(double VA) {
        this.VA = VA;
    }

    public void setVR(double VR) {
        this.VR = VR;
    }

    public void setVT(double VT) {
        this.VT = VT;
    }

    public void setPlanoSaude(double planoSaude) {
        this.planoSaude = planoSaude;
    }

    public void setPlanoOdontologico(double planoOdontologico) {
        this.planoOdontologico = planoOdontologico;
    }
    public void tiposBeneficios(){
        if(setor.equals(Setor.ATENDIMENTO_AO_CLIENTE) || setor.equals(Setor.VENDAS)) {
            planoOdontologico -= 1000;
        } else if(setor.equals(Setor.FINANCEIRO) || setor.equals(Setor.ALMOXARIFADO) || setor.equals(Setor.GESTAO_DE_PESSOAS)) {
            VA += 200;
            VR += 200;
            planoSaude += 500;
            planoOdontologico -= 500;
        } else if(setor.equals(Setor.GERENCIA)) {
            VA += 700;
            VR += 700;
            planoSaude += 1200;
        }
    }
    public double calcularValorIR(){
        double base = salariobase;
        if (base <= 0) return 0.0; // Imposto é 0 para base negativa ou zero

        if (base <= 2428.80) {
            return 0.0; // Isento - valor do imposto é zero
        } else if (base <= 2826.65) { // De R$ 2.428,81 até R$ 2.826,65
            return (base * 0.075) - 182.16;
        } else if (base <= 3751.05) { // De R$ 2.826,66 até R$ 3.751,05
            return (base * 0.15) - 394.16;
        } else if (base <= 4664.68) { // De R$ 3.751,06 até R$ 4.664,68
            return (base * 0.225) - 675.49;
        } else { // Acima de R$ 4.664,68
            return (base * 0.275) - 908.75;
        }
    }

    public double salarioLiquido(){
        return salariobase - calcularValorIR();
    }

    public double calcularPl(){
        return 0.0;
    }

    public String toString(){
        return ("ID: " + id + "\nNome: " + nome + "\nIdade: " + idade + "\nGenero: " + genero + "\nSetor: " + setor + "\nCalculo Imposto: " + calcularValorIR()+ "\nSalário Base: " + salariobase);
    }
}
