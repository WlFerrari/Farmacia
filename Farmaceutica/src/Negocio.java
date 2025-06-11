import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Negocio {
    private static int contadorId = 0;
    private final int id;
    private final Tipo tipo;
    private Status status;
    private final List<ItemNegocio> itemsNegocio;

    private final List<Funcionario> participantes;
    private final Transportadora transportadora;
    private Status status;
    private boolean isAtivo;

    public Negocio(Tipo tipo, Status status, List<ItemNegocio> itemsNegocio, List<Funcionario> participantes, Transportadora transportadora) {
        this.id = ++contadorId;
        this.tipo = tipo;
        this.status = status;
        this.itemsNegocio = itemsNegocio;
        isAtivo = true;

        //diminui a quantidade de cada produto em estoque quando a negociação for de venda
        if(tipo.equals(Tipo.VENDA)){
            for(ItemNegocio itemNegocio : itemsNegocio){
                itemNegocio.getProduto().setQuantidadeEstoque(itemNegocio.getProduto().getQuantidadeEstoque() - itemNegocio.getQuantidade());
            }
        }

        this.participantes = participantes;
        this.transportadora = transportadora;
    }
    
  
    @Override
    public String toString() {
        String nomesParticipantes = participantes.stream()
                .map(Funcionario::getNome)
                .collect(Collectors.joining(", "));

        String produtos = itemsNegocio.stream()
                .map(ItemNegocio::toString)
                .collect(Collectors.joining(", "));

        return String.format(
                "ID do Negócio: %d\nTipo: %s\nStatus: %s\nProdutos: [%s]\nTransportadora: %s\nParticipantes: [%s]",
                id, tipo, status, produtos, transportadora.getNome(), nomesParticipantes
        );
    }

    /**
     * Guia o usuário passo a passo para criar um novo objeto Negocio.
     *
     * @param scanner O objeto Scanner para ler a entrada do usuário.
     * @return um novo objeto Negocio ou null se a criação for cancelada.
     */
    public static Negocio negocioPrompt(Scanner scanner) {
        BancoDeDados db = BancoDeDados.getInstanciaBanco();

        System.out.println("\n--- Criação de Novo Negócio ---");

        // 1. Selecionar o Tipo de Negócio ---------------------------------------------------------------------------------------------
        System.out.println("Selecione o tipo de negócio: ");
        System.out.println("1. Venda");
        System.out.println("2. Compra");
        System.out.print("Opção: ");
        int tipoOpt = Integer.parseInt(scanner.nextLine());
        Tipo tipo = (tipoOpt == 1) ? Tipo.VENDA : Tipo.COMPRA;


        // 2. Selecionar produtos ---------------------------------------------------------------------------------------------
        List<ItemNegocio> produtos = new ArrayList<>();
        if (db.getProdutos().isEmpty()) {
            System.out.println("ERRO: Não há produtos cadastrados. Cancele e cadastre um produto primeiro.");
            return null;
        }

        while (true) {
            System.out.println("\n--- Seleção de Produto ---");
            db.listarProdutos();
            System.out.print("Digite o ID de um produto para adicionar ao negócio (ou digite 'fim' para continuar): ");
            String inputId = scanner.nextLine();

            if (inputId.equalsIgnoreCase("fim")) {
                if (produtos.isEmpty()) {
                    System.out.println("É necessário adicionar pelo menos um produto.");
                    continue; // Força o usuário a adicionar pelo menos um
                }
                break;
            }

            try {
                int idProduto = Integer.parseInt(inputId);
                Produto p = db.buscarProdutoPorId(idProduto);
                if (p != null) {
                    System.out.print("Informe a quantidade deste produto: ");
                    int quant = Integer.parseInt(scanner.nextLine());
                    ItemNegocio item = new ItemNegocio(p, quant);
                    produtos.add(item);
                    System.out.printf("-> '%d' unidades do Produto '%s' adicionado.%n", item.getQuantidade(), item.getProduto().getNome());
                } else {
                    System.out.println("-> ID de produto não encontrado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("-> Entrada inválida. Digite um ID numérico ou 'fim'.");
            }
        }


        // 3. Selecionar Funcionários Participantes ---------------------------------------------------------------------------------------------
        List<Funcionario> participantes = new ArrayList<>();
        if (db.getFuncionarios().isEmpty()) {
            System.out.println("ERRO: Não há funcionários cadastrados. Cancele e cadastre um funcionário primeiro.");
            return null;
        }

        while (true) {
            System.out.println("\n--- Seleção de Funcionários ---");
            db.listarFuncionariosResumido();
            System.out.print("Digite o ID de um funcionário para adicionar ao negócio (ou digite 'fim' para continuar): ");
            String inputId = scanner.nextLine();

            if (inputId.equalsIgnoreCase("fim")) {
                if (participantes.isEmpty()) {
                    System.out.println("É necessário adicionar pelo menos um funcionário.");
                    continue; // Força o usuário a adicionar pelo menos um
                }
                break;
            }

            try {
                int idFunc = Integer.parseInt(inputId);
                Funcionario f = db.getFuncionarioPorId(idFunc);
                if (f != null) {
                    if (!participantes.contains(f)) {
                        participantes.add(f);
                        System.out.printf("-> Funcionário '%s' adicionado.%n", f.getNome());
                    } else {
                        System.out.println("-> Este funcionário já foi adicionado.");
                    }
                } else {
                    System.out.println("-> ID de funcionário não encontrado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("-> Entrada inválida. Digite um ID numérico ou 'fim'.");
            }
        }

        // 4. Selecionar a Transportadora ---------------------------------------------------------------------------------------------
        if (db.getTransportadoras().isEmpty()) {
            System.out.println("ERRO: Não há transportadoras cadastrados. Cancele e cadastre uma transportadora primeiro.");
            return null;
        }

        Transportadora transportadoraSelecionada = null;
        while (transportadoraSelecionada == null) {
            System.out.println("\n--- Seleção de Transportadora ---");
            db.listarTransportadoras();
            System.out.print("Digite o ID da transportadora desejada: ");
            int idTransportadora = scanner.nextInt();
            transportadoraSelecionada = db.buscarTransportadoraPorId(idTransportadora);

            if (!db.getTransportadoras().contains(transportadoraSelecionada)) {
                System.out.println("-> ID da transportadora não encontrado. Tente novamente.");
            }
        }

        // Criação do objeto final com status ABERTO por padrão
        return new Negocio(tipo, Status.ABERTO, produtos, participantes, transportadoraSelecionada);
    }

    public void atualizarStatus(int id, Status status) {
        BancoDeDados db = BancoDeDados.getInstanciaBanco();
        double aux = 0;
        if (status.equals(Status.CONCLUIDO)) {
            setStatus(Status.CONCLUIDO);

            if(getTipo().equals(Tipo.VENDA)){
                aux = verificarValorVendaNegociacao();
                db.addValorCaixa(aux);
            }else{

                //Verifica o total da compra a ser realizada
                for(ItemNegocio item : itemsNegocio){
                    aux += item.getProduto().getValorCompra() * item.getQuantidade();
                }
                //Remove do caixa o valor da compra que foi efetuada
                db.removeValorCaixa(aux);

                //aumenta a quantidade de prodos em estoque quando uma operação de compra for efetivada
                for(ItemNegocio itemNegocio : itemsNegocio){
                    itemNegocio.getProduto().setQuantidadeEstoque(itemNegocio.getProduto().getQuantidadeEstoque() + itemNegocio.getQuantidade());
                }
            }



            System.out.println("Status do negócio atualizado com sucesso para concluido.");
        }else if (status.equals(Status.CANCELADO)) {
            setStatus(Status.CANCELADO);
            for (ItemNegocio item : itemsNegocio) {
                item.getProduto().setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() + item.getQuantidade());
            }
            System.out.println("Status do negócio atualizado com sucesso para cancelado.");
        }else{
            System.out.println("Status de negócio inválido.");
        }
    }

    public double verificarLucroNegociacao(){
        double lucro = 0;
        for(ItemNegocio item : itemsNegocio) {
            lucro += (item.getProduto().getValorVenda() - item.getProduto().getValorCompra()) * item.getQuantidade();
        }
        return lucro;
    }

    public double verificarValorVendaNegociacao(){
        double valor = 0;
        for(ItemNegocio item : itemsNegocio) {
            valor += item.getProduto().getValorVenda() * item.getQuantidade();
        }
        return valor;
    }

  
    // Getters e Setters
    public int getId() {
        return id;
    }

    public Tipo getTipo() {
        return tipo;
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

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }
    
    public List<ItemNegocio> getItemsNegocio() {
        return itemsNegocio;
    }

    }

}