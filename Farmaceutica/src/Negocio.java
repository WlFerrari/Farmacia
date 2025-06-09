import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class Negocio {
    private static int counter = 0;
    private int id;
    private Tipo tipo;
    private Status status;
    private List<Funcionario> participantes;
    private Transportadora transportadora;

    public Negocio(Tipo tipo, Status status, List<Funcionario> participantes, Transportadora transportadora) {
        id = ++counter;
        this.tipo = tipo;
        this.status = status;
        this.participantes = participantes;
        this.transportadora = transportadora;
        if (participantes == null || participantes.isEmpty()) {
            throw new IllegalArgumentException("Um negócio deve ter ao menos um participante.");
        }
        if (transportadora == null) {
            throw new IllegalArgumentException("A transportadora não pode ser nula.");
        }
    }

    public static Negocio negocioPromt(){
        Scanner in = new Scanner(System.in);
        //Pegar instancia do BancoDeDados
        BancoDeDados bd = BancoDeDados.getInstanciaBanco();

        System.out.print("\nInforme o tipo do negocio (compra/venda): ");
        Tipo tipo = Tipo.valueOf(in.nextLine().toUpperCase());

        System.out.print("Informe o status do negocio (aberto/concluido/cancelado): ");
        Status status = Status.valueOf(in.nextLine().toUpperCase());
        System.out.println();

        int i = 1;
        ArrayList<Funcionario> participantes = new ArrayList<>();
        while (i==1){

            //Checa se o usuário já adicionou algum participante do negocio
            //Se já, mostra os dados daqueles já cadastrados
            if(!participantes.isEmpty()){
                System.out.println("Participantes já adicionados:");
                for (Funcionario f : participantes){
                    System.out.println(f.toStringResumido());
                    System.out.println();
                }
            }
            System.out.print("Informe o id do funcionário para adicionar responsável pelo negócio: \n(Informe 0 para remover responsável) ");
            int id = Integer.parseInt(in.nextLine());

            //Verifica se o usuário deseja remover um participante que ele já informou, pois pode ter cadastrado o funcionário errado
            //Caso não tenha escolhido remover, faz a lógica de adição do participante
            if(id == 0){
                if(!participantes.isEmpty()){
                    participantes.removeLast();
                }else System.out.println("Primeiro adicione responsáveis pelo negócio");
            }else{

                Funcionario f = bd.getFuncionarioPorId(id);

                if(f != null){
                    participantes.add(f);
                }else{
                    System.out.println("Erro - Não existe funcionário com o ID informado.");
                }
            }
            System.out.print("\nDeseja adicionar mais algum funcionário responsável? (1=S / 2=N): ");
            i = Integer.parseInt(in.nextLine());
        }
        System.out.println("-------------------------------------");
        System.out.println("Transportadoras cadastradas: \n");
        int j = 0;
        for(Transportadora t : bd.getTransportadoras()){
            System.out.println("Indice "+j+":");
            System.out.println(t.toString());
            System.out.println();
        }
        System.out.print("\nInforme o indice que corresponda à transportadora responsável pelo negócio: ");
        j = Integer.parseInt(in.nextLine());
        Transportadora transportadoraResponsavel = bd.getTransportadoras().get(j);

        return new Negocio(tipo, status, participantes, transportadoraResponsavel);

    }

    public int getId() {
        return id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Funcionario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Funcionario> participantes) {
        this.participantes = participantes;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public String participantesToString(){
        String part = "";

        for(Funcionario f : participantes){
            part += "\n    ID: "+f.getId()+" | Nome: "+f.getNome()+"\n    setor: "+f.getSetor()+"\n";
        }
        return part;
    }

    @Override
    public String toString() {
        return "Negócio ID: " + id +
                "\nTipo: " + tipo +
                "\nStatus: " + status +
                "\nParticipantes: \n" + participantesToString() +
                "\nTransportadora: " + transportadora.getNome();
    }

}
