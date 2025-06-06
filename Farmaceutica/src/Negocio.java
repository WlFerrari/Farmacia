import java.util.UUID;

public class Negocio {
    private final UUID id;
    private Tipo tipo;
    private Status status;
    private List<Funcionario> participantes;
    private Transportadora transportadora;

    public Negocio(Tipo tipo, Status status, List<Funcionario> participantes, Transportadora transportadora) {
        this.id = UUID.randomUUID(); // Gerar um id aleatórios único
        this.tipo = tipo;
        this.status = status;
        this.participantes = participantes;
        this.transportadora = transportadora;
    }

    public UUID getId() {
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
}
