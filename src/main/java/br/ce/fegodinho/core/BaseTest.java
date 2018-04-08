package br.ce.fegodinho.core;

import static br.ce.fegodinho.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
	@After
	public void finalizacao() {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}		
	}

}
