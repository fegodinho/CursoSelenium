import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finalizacao() {
		driver.quit();
	}
	
	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");		
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testTextFieldDuplo(){
		dsl.escreve("elementosForm:nome", "Felipe");
		Assert.assertEquals("Felipe", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Godinho");
		Assert.assertEquals("Godinho", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteregirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste de escrita\nContinuacao\nLinha final.");
		Assert.assertEquals("Teste de escrita\nContinuacao\nLinha final.", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteregirComRadioButton() {
		dsl.clicarRadioButton("elementosForm:sexo:0");		
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
		
	@Test
	public void deveInteregirComCheckBox() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckBoxMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteregirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCampo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));		
	}
	
	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
			
}
