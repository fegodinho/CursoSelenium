package br.ce.fegodinho.test;
import static br.ce.fegodinho.core.DriverFactory.getDriver;
import static br.ce.fegodinho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.fegodinho.core.DSL;

public class TestePrime {
	
	private DSL dsl;
		
	@Before
	public void inicializa() {
		dsl = new DSL();
	}
	
	@After
	public void finalizacao() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadioButton(By.xpath("//input[@id='j_idt117:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt117:console:0"));
		dsl.clicarRadioButton(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt117:console:1"));		
		
	}
	
	@Test
	public void deveInteragirComComboPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt117:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt117:console_label"));				
	}

}
