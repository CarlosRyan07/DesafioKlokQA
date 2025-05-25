# 🛒 Testes Automatizados - Magazine Luiza

Este projeto foi desenvolvido como parte do Desafio de Estágio da Klok, que propõe a criação de testes automatizados de ponta a ponta em um grande portal de comércio online. A proposta inclui cobrir casos de sucesso e fluxos alternativos, avaliando a organização do código e a forma como os testes interagem com os elementos da aplicação.

---

## 🎯 Contexto do Desafio

Desafio Klok – Automação Web
Automatizar o seguinte fluxo:

1. Acessar um grande portal de e-commerce (ex: Americanas, Submarino, Magazine Luiza).

2. Realizar uma busca por um produto.

3. Validar o retorno da busca.

## 📌 O que está sendo testado?

### ✅ Funcionalidade

- 🔍 Busca com termos válidos
- ❌ Busca com termos inexistentes
- 🈳 Campo de busca vazio
- 📏 Entrada com texto extremamente longo

### 🔐 Robustez e Segurança

- 🧪 Injeção de HTML e JavaScript (XSS)
- 🌐 Unicode e Emojis
- 🧱 Strings com padrão de injeção SQL

### 📱 Usabilidade e Desempenho

- 📲 Responsividade em resoluções mobile
- ⏱️ Tempo de resposta da busca

---

## 🧰 Tecnologias Utilizadas

- ☕ **Java 17**
- 📦 **Maven**
- 🌐 **Selenium WebDriver**
- 🧪 **JUnit 5**

---

## ✅ Pré-requisitos

Certifique-se de ter instalado:

- Java 17 ou superior  
- Maven  
- Google Chrome (última versão recomendada)

---

## 🚀 Como Executar os Testes

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
    ```

2. **Execute os testes:**

   ```bash
   mvn test
   ```

> Os testes são executados automaticamente via Maven com o WebDriver gerenciado pelo Selenium Manager.

---

## 🗂️ Estrutura do projeto

```
projeto/
├── pom.xml
├── consultaSql.txt
└── src
    ├── main
    │   └── java
    └── test
        └── java
            └── br
                └── com
                    └── projetoKlok
                        ├── pages
                        │   └── MagazineLuizaHomePage.java
                        └── tests
                            └── MagazineLuizaTest.java
```

---

## 🔧 Detalhes das Classes

### 1. MagazineLuizaHomePage

Representa a **página inicial da Magazine Luiza** e encapsula a interação com seus elementos.

**Principais métodos:**

- `acessarHomePage()`: Acessa a página principal da loja.
- `buscarProduto(String termo)`: Digita o termo no campo de busca e executa a pesquisa.
- `resultadoContemTexto(String textoEsperado)`: Verifica se o texto esperado está presente nos resultados.
- `mensagemDeProdutoInexistente()`: Confirma se a mensagem de "produto inexistente" foi exibida.

---

### 2. MagazineLuizaTest

Contém a **suite de testes automatizados** com JUnit 5, estruturada em categorias de validação.

**Configuração:**

- `@BeforeEach`: Abre o navegador e inicializa a `MagazineLuizaHomePage`.
- `@AfterEach`: Fecha o navegador ao final de cada teste.

## 📝 Organização dos testes

Os testes estão organizados com anotações `@Order` para facilitar a leitura e execução ordenada dos casos.

```java
@Test @Order(1)
public void testBuscaValida() {
    // ...
}
```	

## 📸 Evidências dos Testes

![Resultados](imgs\resultadoPlay.png) 

![Resultados](imgs\resultadoMVN.png) 
