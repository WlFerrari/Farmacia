# Sistema de Gestão para Farmácia  фармацевт

![Linguagem](https://img.shields.io/badge/Linguagem-Java-blue?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=for-the-badge)

## 📝 Sobre o Projeto

Este projeto é um **Sistema de Gestão para Farmácia** desenvolvido em Java, operando inteiramente via console (CLI). Foi criado como um trabalho acadêmico para a disciplina de Programação Orientada a Objetos, com o objetivo de aplicar na prática os conceitos fundamentais do paradigma, como encapsulamento, polimorfismo, e o uso de padrões de projeto.

O sistema simula o gerenciamento de uma farmácia, controlando entidades como funcionários, produtos, transportadoras e as negociações de compra e venda. Para garantir a consistência dos dados durante a execução, o projeto utiliza um **banco de dados em memória** implementado com o padrão de projeto **Singleton**.

---

## ✨ Funcionalidades Principais

O sistema permite ao usuário realizar as seguintes operações através de um menu interativo:

* **Gerenciamento de Funcionários:**
    * Adicionar novos funcionários.
    * Remover funcionários por ID.
    * Listar todos os funcionários com detalhes (salário, benefícios, etc.).
    * Filtrar e listar funcionários por setor.

* **Gerenciamento de Produtos:**
    * Adicionar novos produtos ao estoque.
    * Remover produtos por ID.
    * Atualizar a quantidade em estoque de um produto.
    * Atualizar o preço de venda.
    * Listar todos os produtos com seus detalhes.

* **Gerenciamento de Transportadoras:**
    * Cadastrar novas transportadoras e suas regiões de atendimento.
    * Listar e editar informações das transportadoras cadastradas.

* **Gerenciamento de Negócios:**
    * Criar novas negociações (compra ou venda).
    * Associar funcionários e transportadoras a uma negociação.
    * Listar todas as negociações ou filtrar por status (Aberto, Concluído, Cancelado).

* **Relatórios Financeiros:**
    * Exibir o valor atual no caixa.
    * Calcular e exibir a estimativa de lucro mensal e anual com base no estoque atual.

---

## 💻 Tecnologias e Conceitos Aplicados

* **Linguagem:** Java (JDK 11 ou superior)
* **Paradigma:** Programação Orientada a Objetos (POO)
* **Padrão de Projeto (Design Pattern):**
    * **Singleton:** Utilizado na classe `BancoDeDados` para garantir uma única instância que centraliza o acesso a todos os dados da aplicação, simulando um repositório central em memória.
* **Estrutura de Dados:** `ArrayList` para armazenar e gerenciar as coleções de objetos.
* **Ambiente de Execução:** Aplicação de Console (Interface de Linha de Comando - CLI).

---

## 🚀 Como Executar o Projeto

Para compilar e executar o projeto localmente, siga os passos abaixo.

### Pré-requisitos

* Ter o **Java Development Kit (JDK)** na versão 11 ou superior instalado em sua máquina. Você pode verificar a instalação com o comando `java -version`.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU-USUARIO/NOME-DO-SEU-REPOSITORIO.git](https://github.com/SEU-USUARIO/NOME-DO-SEU-REPOSITORIO.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd NOME-DO-SEU-REPOSITORIO
    ```

3.  **Compile os arquivos `.java`:**
    * Se todos os arquivos estiverem na pasta raiz:
        ```bash
        javac *.java
        ```
    * Se os arquivos estiverem organizados em pastas (ex: `model/`, `enums/`):
        ```bash
        javac */*.java *.java
        ```
    * Um comando mais robusto que funciona para qualquer estrutura de pastas:
        ```bash
        find . -name "*.java" | xargs javac
        ```

4.  **Execute a classe principal:**
    ```bash
    java Main
    ```

Após executar o último comando, o menu interativo do sistema será exibido no seu terminal.

---

## 🤝 Contribuidores

Este projeto foi desenvolvido com a colaboração e o esforço dos seguintes membros da equipe:

* **[João Vitor Ferrari]** - ([@WlFerrari](https://github.com/WlFerrari))
* **[Guilherme Pinheiro Moura]** - ([@guilhermepmoura](https://github.com/guilhermepmoura))
* **[Ayrton Itiro Kobo]** - ([@itiro-y](https://github.com/itiro-y))
* **[Kevin Luiz]** - ([@KevinLuiz10](https://github.com/KevinLuiz10))
* **[Mateus Kenzo]** - ([@matheuskya](https://github.com/matheuskya))

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
