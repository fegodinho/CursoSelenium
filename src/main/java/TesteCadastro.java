import static br.ce.fegodinho.core.DriverFactory.getDriver;
import static br.ce.fegodinho.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteCadastro {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finalizacao() {
		killDriver();
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
		
		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		Assert.assertEquals("Felipe",page.obterNomeCadastro());
		Assert.assertEquals("Godinho", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza",page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
		
	}
}
