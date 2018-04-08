package br.ce.fegodinho.test;
import static br.ce.fegodinho.core.DriverFactory.getDriver;
import static br.ce.fegodinho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.fegodinho.core.DSL;

public class TesteFrames {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finalizacao() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!",msg);		
		dsl.sairFrame();	
		dsl.escreve("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragircomFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!",msg);
	}

}
