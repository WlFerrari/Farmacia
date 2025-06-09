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
    private BancoDeDados(){
        this.funcionarios = new ArrayList<Funcionario>();
        this.produtos = new ArrayList<Produto>();
        this.transportadoras = new ArrayList<Transportadora>();
        this.negocios = new ArrayList<Negocio>();
        caixa = new Caixa(200000);
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
        if (novoFuncionario != null) {
            for (int i = 0; i < funcionarios.size(); i++) {
                if (id == funcionarios.get(i).getId()) {
                    funcionarios.set(i, novoFuncionario);
                    break;
                }
            }
        }
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
            funcionarios.removeIf(funcionario -> id == funcionario.getId());
    }

    public void removerProduto(int id) {
            produtos.removeIf(produto -> id == produto.getId());
    }

    public void removerNegocioPorId(int id) {
            negocios.removeIf(negocio -> id == negocio.getId());
    }

    public void removerTransportadoraPorNome(String nome) {
        if (nome != null) {
            transportadoras.removeIf(transportadora -> nome.equals(transportadora.getNome()));
        }
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
                if (id == produto.getId()) {
                    return produto;
                }
            }
        return null;
    }

    public Negocio buscarNegocioPorId(int id) {

            for (Negocio negocio : negocios) {
                if (id == negocio.getId()) {
                    return negocio;
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


    //Metodos para listar detalhadamente os dados de cada objeto das listas -------------------------------------------

    public void listarFuncionarios() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("Listagem de funcionarios: \n");
        for (Funcionario f : funcionarios) {
            System.out.println(f.toString());
            System.out.println();
        }
    }

    public void listarProdutos() {
        System.out.println("\n----------------------------------------------------");
        System.out.println("Listagem de produtos: \n");
        for (Produto p : produtos) {
            System.out.println(p.toString());
            System.out.println();
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
            System.out.println();
        }
    }

    public void listarFuncionarioPorSetor(Setor setor) {
        System.out.println();
        for(Funcionario f : funcionarios) {
            if(f.getSetor().equals(setor)){
                System.out.println(f.toString());
                System.out.println();
            }
        }
    }
}
