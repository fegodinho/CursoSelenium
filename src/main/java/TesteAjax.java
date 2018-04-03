import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL(driver);
	}
	
	@After
	public void finalizacao() {
		//driver.quit();
	}
	
	@Test
	public void testAjax() {
		dsl.escreve("j_idt116:name", "Teste");
		dsl.clicarBotao("j_idt116:j_idt119");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt116:display"), "Teste"));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt129")));
		
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt116:display"));
	}

}
