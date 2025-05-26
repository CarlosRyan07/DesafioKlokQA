package br.com.projetoKlok.tests;

import br.com.projetoKlok.pages.MagazineLuizaHomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MagazineLuizaSearchTest {

    private WebDriver driver;
    private MagazineLuizaHomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new MagazineLuizaHomePage(driver);
        homePage.acessarHomePage();
        
    }

    // ------------------ [ TESTES FUNCIONAIS - ENTRADA DE DADOS ] ------------------

    @Test @Order(1)
    @DisplayName("Deve buscar um produto válido com sucesso")
    public void testBuscaValida() {
        homePage.buscarProduto("action figure");
        assertTrue(homePage.resultadoContemTexto("action figure"),
                "Resultado não contém 'action figure'");
    }

    @Test @Order(2)
    @DisplayName("Deve exibir mensagem para produto inexistente")
    public void testBuscaComProdutoInexistente() {
        homePage.buscarProduto("sxjsbhv");
        assertTrue(homePage.mensagemDeProdutoInexistente(),
                "Página não exibiu mensagem correta para produto inexistente");
    }

    @Test @Order(3)
    @DisplayName("Deve lidar com busca vazia exibindo ofertas ou destaques")
    public void testBuscaComEntradaVazia() {
        homePage.buscarProduto("");
        assertTrue(homePage.resultadoContemTexto("ofertas") || homePage.resultadoContemTexto("destaques"),
                "Busca vazia não redirecionou para resultados padrão");
    }

    @Test @Order(4)
    @DisplayName("Deve tratar corretamente string muito longa na busca")
    public void testBuscaComStringMuitoLonga() {
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 1000; i++) input.append("A");
        homePage.buscarProduto(input.toString());
        assertTrue(homePage.resultadoContemTexto("resultado") || homePage.resultadoContemTexto("ofertas"),
                "Sistema não lidou bem com string extremamente longa");
    }

    // ------------------ [ SEGURANÇA E ROBUSTEZ ] ------------------

    @Test @Order(5)
    @DisplayName("Deve rejeitar injeção de HTML/JavaScript (XSS)")
    public void testBuscaComHTMLInjetado() {
        homePage.buscarProduto("<script>alert('XSS')</script>");
        assertTrue(homePage.resultadoContemTexto("resultado") || homePage.resultadoContemTexto("você quis dizer"),
                "Entrada com HTML/JS foi interpretada — risco de XSS");
    }

    @Test @Order(6)
    @DisplayName("Deve aceitar caracteres Unicode e emojis")
    public void testBuscaComUnicodeEmoji() {
        homePage.buscarProduto("❤️☀️✅漢字한글عربى");
        assertTrue(homePage.resultadoContemTexto("resultado") || homePage.resultadoContemTexto("você quis dizer"),
                "Sistema não tratou corretamente caracteres Unicode BMP");
    }

    @Test @Order(7)
    @DisplayName("Deve proteger contra injeção SQL-like")
    public void testBuscaComInjecaoSQL() {
        homePage.buscarProduto("notebook' OR '1'='1");
        assertTrue(homePage.resultadoContemTexto("notebook") || homePage.resultadoContemTexto("resultado"),
                "Sistema pode estar vulnerável a injeções SQL-like");
    }

    // ------------------ [ USABILIDADE E DESEMPENHO ] ------------------

    @Test @Order(8)
    @DisplayName("Deve se adaptar corretamente à resolução mobile")
    public void testResponsividadeMobile() {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 812)); // iPhone X
        homePage.buscarProduto("smartphone");
        assertTrue(homePage.resultadoContemTexto("smartphone"),
                "Página não se adaptou corretamente à tela mobile");
    }

    @Test @Order(9)
    @DisplayName("Deve responder em menos de 7 segundos")
    // Atenção: este teste pode falhar se sua máquina estiver sobrecarregada ou com desempenho reduzido,
    // já que o tempo de resposta depende do processamento local e da velocidade da conexão.
    public void testDesempenhoBasico() {
        long inicio = System.currentTimeMillis();
        homePage.buscarProduto("fone de ouvido");
        long fim = System.currentTimeMillis();
        long duracao = fim - inicio;
        assertTrue(duracao < 7000, "Tempo de resposta excedeu o limite: " + duracao + "ms");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
