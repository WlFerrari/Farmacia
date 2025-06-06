import java.util.ArrayList;
import java.util.UUID;

//Classe destinada a armazenar as coleções de objetos que serão usadas durante a execução do projeto
public class BancoDeDados {
    //Primeiro passo do project pattern singleton
    //O singleton serve para garantir que haja uma instância única da classe
    private static BancoDeDados instancia;

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
    public void atualizarFuncionario(UUID id, Funcionario novoFuncionario) {
        if (id != null && novoFuncionario != null) {
            for (int i = 0; i < funcionarios.size(); i++) {
                if (id.equals(funcionarios.get(i).getId())) {
                    funcionarios.set(i, novoFuncionario);
                    break;
                }
            }
        }
    }

    public void atualizarProduto(UUID id, Produto novoProduto) {
        if (id != null && novoProduto != null) {
            for (int i = 0; i < produtos.size(); i++) {
                if (id.equals(produtos.get(i).getId())) {
                    produtos.set(i, novoProduto);
                    break;
                }
            }
        }
    }

    public void atualizarNegocio(UUID id, Negocio novoNegocio) {
        if (id != null && novoNegocio != null) {
            for (int i = 0; i < negocios.size(); i++) {
                if (id.equals(negocios.get(i).getId())) {
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

    public void removerFuncionario(UUID id) {
        if (id != null) {
            funcionarios.removeIf(funcionario -> id.equals(funcionario.getId()));
        }
    }

    public void removerProduto(UUID id) {
        if (id != null) {
            produtos.removeIf(produto -> id.equals(produto.getId()));
        }
    }

    public void removerNegocioPorId(UUID id) {
        if (id != null) {
            negocios.removeIf(negocio -> id.equals(negocio.getId()));
        }
    }

    public void removerTransportadoraPorNome(String nome) {
        if (nome != null) {
            transportadoras.removeIf(transportadora -> nome.equals(transportadora.getNome()));
        }
    }

    //Metodos para retornar o objeto solicitado ----------------------------------------------------------------------

    public Funcionario getFuncionarioPorId(UUID id) {
        if (id != null) {
            for (Funcionario funcionario : funcionarios) {
                if (id.equals(funcionario.getId())) {
                    return funcionario;
                }
            }
        }
        return null;
    }

    public Produto buscarProdutoPorId(UUID id) {
        if (id != null) {
            for (Produto produto : produtos) {
                if (id.equals(produto.getId())) {
                    return produto;
                }
            }
        }
        return null;
    }

    public Negocio buscarNegocioPorId(UUID id) {
        if (id != null) {
            for (Negocio negocio : negocios) {
                if (id.equals(negocio.getId())) {
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


}
