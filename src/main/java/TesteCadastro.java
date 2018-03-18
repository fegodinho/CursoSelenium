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
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finalizacao() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarCasdastroComSucesso() {
		page.setNome("Felipe");
		page.setSobrenome("Godinho");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsportes("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Felipe"));
		Assert.assertEquals("Sobrenome: Godinho", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza",page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsportesCadastro());
		
	}
	
	@Test
	public void verificaNomeObrigatorio() {				
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaSobrenomeObrigatorio() {				
		page.setNome("Nome");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaSexoObrigatorio() {				
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());		
	}
	
	@Test
	public void verificaComidaVegetariana() {				
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());	
	}
	
	@Test
	public void verificaEsporte() {				
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsportes("Natacao","O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());	
	}

}
