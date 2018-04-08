package br.ce.fegodinho.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.fegodinho.core.DriverFactory;
import br.ce.fegodinho.test.TesteCadastro;
import br.ce.fegodinho.test.TesteCampoTreinamento;
import br.ce.fegodinho.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	
}