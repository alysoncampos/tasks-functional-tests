package br.pe.acampos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();

        try {
            //clicar em Add Tdo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assertions.assertEquals("Success!", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();

        try {
            //clicar em Add Tdo
            driver.findElement(By.id("addTodo")).click();

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assertions.assertEquals("Fill the task description", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();

        try {
            //clicar em Add Tdo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assertions.assertEquals("Fill the due date", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();

        try {
            //clicar em Add Tdo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2019");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assertions.assertEquals("Due date must not be in past", message);
        } finally {
            //fechar o browser
            driver.quit();
        }
    }
}
