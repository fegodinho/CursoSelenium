import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreve(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadioButton (String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheckBox (String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isCheckBoxMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarBotão(String id) {
		WebElement botao = driver.findElement(By.id(id));
		botao.click();
	}
	
	public void clicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alert = driver.switchTo().alert();
		String texto =  alert.getText();
		alert.accept();
		return texto;
	}

}
