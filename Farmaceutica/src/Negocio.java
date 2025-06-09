import java.util.List;
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

    @Override
    public String toString() {
        return "Negócio ID: " + id +
                "\nTipo: " + tipo +
                "\nStatus: " + status +
                "\nParticipantes: " + participantes.stream().map(Funcionario::getNome).toList() +
                "\nTransportadora: " + transportadora.getNome();
    }

}
