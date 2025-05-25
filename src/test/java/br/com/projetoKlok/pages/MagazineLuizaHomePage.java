package br.com.projetoKlok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MagazineLuizaHomePage {
    private WebDriver driver;

    public MagazineLuizaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarHomePage() {
        driver.get("https://www.magazineluiza.com.br/");
    }

    public void buscarProduto(String termo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement barraDeBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
        barraDeBusca.sendKeys(termo);
        barraDeBusca.sendKeys(Keys.ENTER);
    }

    public boolean resultadoContemTexto(String textoEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='product-card-container']")));
        return driver.getPageSource().toLowerCase().contains(textoEsperado.toLowerCase());
    }

    public boolean mensagemDeProdutoInexistente() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("h1[data-testid='text-list-title']")
        ));
        return mensagem.getText().toLowerCase().contains("não encontrou resultado");
    }

    
}
