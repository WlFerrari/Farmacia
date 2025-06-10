import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transportadora {
    private static int counter = 0;
    private final int id;
    private String nome;
    private List<String> regioes;
    private boolean isAtivo;

    public Transportadora(String nome, List<String> regioes) {
        this.nome = nome;
        this.regioes = regioes;
        this.id = ++counter;
        isAtivo = true;
    }

    // ATUALIZADO: Recebe o Scanner como parâmetro
    public static Transportadora transportadoraPrompt(Scanner sc) {
        System.out.print("Digite o nome da transportadora: ");
        String nome = sc.nextLine();

        List<String> regioes = new ArrayList<>();
        System.out.println("Digite as regiões de cobertura (digite 'fim' para encerrar):");
        while (true) {
            System.out.print("Região: ");
            String regiao = sc.nextLine();
            if (regiao.equalsIgnoreCase("fim")) {
                break;
            }
            regioes.add(regiao);
        }
        return new Transportadora(nome, regioes);
    }

    // ATUALIZADO: Recebe o Scanner como parâmetro
    public static void editarTransportadora(List<Transportadora> lista, Scanner sc) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma transportadora cadastrada para editar.");
            return;
        }

        System.out.println("Selecione a transportadora para editar:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), lista.get(i).getNome());
        }
        System.out.print("Opção: ");
        int indice = Integer.parseInt(sc.nextLine()) - 1;

        if (indice < 0 || indice >= lista.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Transportadora transportadora = lista.get(indice);
        System.out.printf("Novo nome (ou Enter para manter o atual '%s'): ", transportadora.getNome());
        String novoNome = sc.nextLine();
        if (!novoNome.isBlank()) {
            transportadora.setNome(novoNome);
        }
        System.out.println("Transportadora atualizada com sucesso!");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRegioes() {
        return regioes;
    }

    public void setRegioes(List<String> regioes) {
        this.regioes = regioes;
    }

    public int getId() {
        return id;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTransportadora: " + nome + "\nRegiões de cobertura: " + String.join(", ", regioes);
    }
}