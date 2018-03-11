import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {
	
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
	public void deveRealizarCasdastroComSucesso() {		
		dsl.escreve("elementosForm:nome","Felipe");
		dsl.escreve("elementosForm:sobrenome","Godinho");
		dsl.clicarRadioButton("elementosForm:sexo:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade","Superior");
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.clicarBotão("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Felipe"));
		Assert.assertEquals("Sobrenome: Godinho", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza",dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
		
	}
	
	@Test
	public void verificaNomeObrigatorio() {				
		dsl.clicarBotão("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaSobrenomeObrigatorio() {				
		dsl.escreve("elementosForm:nome","Nome");
		dsl.clicarBotão("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaSexoObrigatorio() {				
		dsl.escreve("elementosForm:nome","Nome");
		dsl.escreve("elementosForm:sobrenome","Sobrenome");
		dsl.clicarBotão("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaComidaVegetariana() {				
		dsl.escreve("elementosForm:nome","Nome");
		dsl.escreve("elementosForm:sobrenome","Sobrenome");
		dsl.clicarRadioButton("elementosForm:sexo:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
		dsl.clicarBotão("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());	
	}
	
	@Test
	public void verificaEsporte() {				
		dsl.escreve("elementosForm:nome","Nome");
		dsl.escreve("elementosForm:sobrenome","Sobrenome");
		dsl.clicarRadioButton("elementosForm:sexo:0");
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotão("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());	
	}

}
