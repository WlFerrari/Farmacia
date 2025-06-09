import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transportadora {
    private String nome;
    private List<String> regioes;

    // Construtor vazio
    public Transportadora() {
        this.regioes = new ArrayList<>();
    }

    // Construtor parametrizado
    public Transportadora(String nome, List<String> regioes) {
        this.nome = nome;
        this.regioes = regioes;
    }

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

    // Método para cadastrar uma nova transportadora
    public static Transportadora transportadoraPrompt() {
        Scanner sc = new Scanner(System.in);
        Transportadora transportadora = new Transportadora();

        System.out.println("Digite o nome da transportadora: ");
        transportadora.setNome(sc.nextLine());

        System.out.println("Digite as regiões de cobertura da transportadora (digite 'fim' para encerrar):");
        String regiao;
        do {
            System.out.print("Região: ");
            regiao = sc.nextLine();
            if (!regiao.equalsIgnoreCase("fim")) {
                transportadora.getRegioes().add(regiao);
            }
        } while (!regiao.equalsIgnoreCase("fim"));

        return transportadora;
    }

    // Método para listar todas as transportadoras
    public static void listarTransportadoras(List<Transportadora> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma transportadora cadastrada.");
        } else {
            System.out.println("\n--- Lista de Transportadoras ---");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + " - " + lista.get(i));
            }
        }
    }

    // Método para editar uma transportadora pelo índice na lista
    public static void editarTransportadora(List<Transportadora> lista) {
        Scanner sc = new Scanner(System.in);

        if (lista.isEmpty()) {
            System.out.println("Nenhuma transportadora cadastrada.");
            return;
        }

        listarTransportadoras(lista);

        System.out.print("Digite o número da transportadora que deseja editar: ");
        int indice = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha

        if (indice < 1 || indice > lista.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Transportadora transportadora = lista.get(indice - 1);

        System.out.println("Editando transportadora: " + transportadora.getNome());

        System.out.print("Novo nome (ou Enter para manter): ");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) {
            transportadora.setNome(novoNome);
        }

        System.out.println("Regiões atuais: " + String.join(", ", transportadora.getRegioes()));
        System.out.print("Deseja editar as regiões? (s/n): ");
        String opcao = sc.nextLine();
        if (opcao.equalsIgnoreCase("s")) {
            transportadora.getRegioes().clear();
            System.out.println("Digite as novas regiões de cobertura (digite 'fim' para encerrar):");
            String regiao;
            do {
                System.out.print("Região: ");
                regiao = sc.nextLine();
                if (!regiao.equalsIgnoreCase("fim")) {
                    transportadora.getRegioes().add(regiao);
                }
            } while (!regiao.equalsIgnoreCase("fim"));
        }

        System.out.println("Transportadora atualizada com sucesso!");
    }

    @Override
    public String toString() {
        return "Nome da transportadora: " + nome + "\nRegiões de cobertura: " + String.join(", ", regioes);
    }
}
