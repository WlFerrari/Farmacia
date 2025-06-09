import java.util.ArrayList;

//Classe destinada a armazenar as coleções de objetos que serão usadas durante a execução do projeto
public class BancoDeDados {
    //Primeiro passo do project pattern singleton
    //O singleton serve para garantir que haja uma instância única da classe
    private static BancoDeDados instancia;
    private Caixa caixa;

    //ArraysList para armazenar os objetos que serão persistidos durante a execução do sistema
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Produto> produtos;
    private ArrayList<Transportadora> transportadoras;
    private ArrayList<Negocio> negocios;


    // Construtor privado para evitar instanciamento externo
    private BancoDeDados() {
        this.funcionarios = new ArrayList<Funcionario>();
        this.produtos = new ArrayList<Produto>();
        this.transportadoras = new ArrayList<Transportadora>();
        this.negocios = new ArrayList<Negocio>();
        caixa = new Caixa(200000);

        // Produtos iniciais da farmácia
        produtos.add(new Produto("Dipirona 500mg", 1.50, 3.00, 100));
        produtos.add(new Produto("Paracetamol 750mg", 1.80, 3.50, 120));
        produtos.add(new Produto("Ibuprofeno 400mg", 2.20, 4.50, 80));
        produtos.add(new Produto("Amoxicilina 500mg", 3.00, 6.00, 60));
        produtos.add(new Produto("Omeprazol 20mg", 2.00, 4.00, 90));
        produtos.add(new Produto("Loratadina 10mg", 1.60, 3.20, 75));
        produtos.add(new Produto("Simeticona 125mg", 1.90, 3.80, 50));
        produtos.add(new Produto("Ácido Acetilsalicílico 100mg", 1.40, 2.80, 110));
        produtos.add(new Produto("Cloridrato de Sertralina 50mg", 4.00, 8.00, 30));
        produtos.add(new Produto("Metformina 850mg", 2.50, 5.00, 70));


    }

    // Método público para obter a instância única
    public static BancoDeDados getInstanciaBanco() {
        if (instancia == null) {
            instancia = new BancoDeDados();
        }
        return instancia;
    }


    //Metodos Getters -------------------------------------------------------------------------------------------------
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public ArrayList<Negocio> getNegocios() {
        return negocios;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    //Metodos para inclusão de items nas listas ----------------------------------------------------------------------
    public void adicionarFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            funcionarios.add(funcionario);
        }
    }

    public void adicionarProduto(Produto produto) {
        if (produto != null) {
            produtos.add(produto);
        }
    }

    public void adicionarTransportadora(Transportadora transportadora) {
        if (transportadora != null) {
            transportadoras.add(transportadora);
        }
    }

    public void adicionarNegocio(Negocio negocio) {
        if (negocio != null) {
            negocios.add(negocio);
        }
    }


    //Metodos para alteração de item nas listas------------------------------------------------------------------------
    public void atualizarFuncionario(int id, Funcionario novoFuncionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (id == funcionarios.get(i).getId()) {
                funcionarios.set(i, novoFuncionario);
                break;
            }
        }
    }

    public boolean atualizarEstoqueProduto(int id, int novoEstoque) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produto.setQuantidadeEstoque(novoEstoque);
            return true;
        }
        return false;
    }

    public boolean atualizarPrecoProduto(int id, double novoPreco) {
        Produto produto = buscarProdutoPorId(id);
        if (produto != null) {
            produto.setValorVenda(novoPreco);
            return true;
        }
        return false;
    }

    public void atualizarProduto(int id, Produto novoProduto) {
        if (novoProduto != null) {
            for (int i = 0; i < produtos.size(); i++) {
                if (id == produtos.get(i).getId()) {
                    produtos.set(i, novoProduto);
                    break;
                }
            }
        }
    }

    public void atualizarNegocio(int id, Negocio novoNegocio) {
        if (novoNegocio != null) {
            for (int i = 0; i < negocios.size(); i++) {
                if (id == negocios.get(i).getId()) {
                    negocios.set(i, novoNegocio);
                    break;
                }
            }
        }
    }

    public void atualizarTransportadoraPorNome(String nome, Transportadora novaTransportadora) {
        if (nome != null && novaTransportadora != null) {

            for (int i = 0; i < transportadoras.size(); i++) {
                if (nome.equals(transportadoras.get(i).getNome())) {
                    transportadoras.set(i, novaTransportadora);
                    break;
                }
            }

        }
    }

    //Metodos para deletar um objeto dos Arrays ----------------------------------------------------------------------

    public void removerFuncionario(int id) {
        funcionarios.removeIf(funcionario -> funcionario.getId() == id);
    }

    public boolean removerProduto(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }

    public void removerNegocioPorId(int id) {
        negocios.removeIf(negocio -> negocio.getId() == id);
    }

    public void removerTransportadoraPorNome(String nome) {
        transportadoras.removeIf(transportadora -> transportadora.getNome().equals(nome));
    }

    //Metodos para retornar o objeto solicitado ----------------------------------------------------------------------

    public Funcionario getFuncionarioPorId(int id) {
            for (Funcionario funcionario : funcionarios) {
                if (id == funcionario.getId()) {
                    return funcionario;
                }
            }
        return null;
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public Negocio buscarNegocioPorId(int id) {
        if (id != negocios.get(0).getId()) {
            for (Negocio negocio : negocios) {
                if (id == negocio.getId()) {
                    return negocio;
                }
            }
        }
        return null;
    }

    public Transportadora buscarTransportadoraPorNome(String nome) {
        if (nome != null) {
            for (Transportadora transportadora : transportadoras) {
                if (nome.equals(transportadora.getNome())) {
                    return transportadora;
                }
            }
        }
        return null;
    }

    public void listarFuncionarios() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("Listagem de funcionarios: \n");
        for (Funcionario f : funcionarios) {
            System.out.println(f.toString());
            System.out.println();
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos) {
                System.out.printf("ID: %s | Nome: %s | Compra: R$%.2f | Venda: R$%.2f | Estoque: %d\n",
                        p.getId(), p.getNome(), p.getValorCompra(), p.getValorVenda(), p.getQuantidadeEstoque());
            }
        }
    }

    public void listarTransportadoras() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("Listagem de transportadoras: \n");
        for (Transportadora t : transportadoras) {
            System.out.println(t.toString());
            System.out.println();
        }
    }

    public void listarNegocios() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("Listagem de negocios: \n");
        for (Negocio n : negocios) {
            System.out.println(n.toString());
            System.out.println("------------------");
        }
    }

    public void listarFuncionarioPorSetor(Setor setor) {
        System.out.println();
        for (Funcionario f : funcionarios) {
            if (f.getSetor().equals(setor)) {
                System.out.println(f.toString());
                System.out.println();
            }
        }
    }

    public void listarNegociosPorStatus(Status status) {
        System.out.println();
        System.out.println("Listagem de negocios com o status "+ status.name() +": \n");
        for(Negocio n : negocios) {
            if(n.getStatus().equals(status)){
                System.out.println(n.toString());
                System.out.println();
            }
        }
    }
}
