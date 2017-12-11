import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {

	@Test
	public void Teste() {
		
		//System.setProperty("webdriver.gecko.driver","\\Users\\Felipe Godinho\\Downloads\\Drivers\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");				
		
		//System.setProperty("webdriver.chrome.driver","\\Users\\Felipe Godinho\\Downloads\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver = new EdgeDriver();
		
		//driver.manage().window().setPosition(new Point(100,100));
		//driver.manage().window().setSize(new Dimension(1200, 765));
		//river.manage().window().maximize();
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();

	}

}
