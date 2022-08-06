import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllLevelsSolution {
	
	private static WebDriver driver = null;
	private By startButton = By.id("start_button");
	private By levelTitle = By.cssSelector("h1");

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		
  		WebDriverManager.chromedriver().setup();
  		ArrayList<String> optionsList = new ArrayList<String>();
		ChromeOptions chromeOptions = new ChromeOptions();
		optionsList.add("--start-maximized");
		optionsList.add("--incognito");
		optionsList.add("disable-notifications");
		chromeOptions.addArguments(optionsList);
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
  		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		
		driver = new ChromeDriver(chromeOptions);
		
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		
		//Close browser
		//driver.quit();
		
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		
		//Open URL
		driver.get("http://pruebaselenium.serviciosdetesting.com/");
		WebElement levelTitleElement=driver.findElement(levelTitle);
		
		//Level 1
		assertEquals(levelTitleElement.getText(),"Práctica Selenium");
		WebElement startButtonElement=driver.findElement(startButton);
		startButtonElement.click();
		levelTitleElement=driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(),"Level 2");
		
		//Level 2
		WebElement textBoxElement = driver.findElement(By.id("input"));
		textBoxElement.sendKeys("selenium");
		WebElement continueButtonElement = driver.findElement(By.id("next"));
		continueButtonElement.click();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 3");
		
		//Level 3
		String xpath = "/html[1]/body[1]/div[1]/div[1]/label[1]";
		textBoxElement = driver.findElement(By.xpath(xpath));
		String text = textBoxElement.getText();
		textBoxElement = driver.findElement(By.id("input"));
		textBoxElement.sendKeys(text);
		continueButtonElement = driver.findElement(By.id("next"));
		continueButtonElement.click();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 4");
		
		//Level 4
		xpath = "//a[contains(text(),'Botón 1')]";
		WebElement buttonElement = driver.findElement(By.xpath(xpath));
		buttonElement.click();
		xpath = "//a[contains(text(),'Botón 2')]";
		buttonElement = driver.findElement(By.xpath(xpath));
		buttonElement.click();
		xpath = "//a[contains(text(),'Botón 3')]";
		buttonElement = driver.findElement(By.xpath(xpath));
		buttonElement.click();
		xpath = "//a[contains(text(),'Botón 4')]";
		buttonElement = driver.findElement(By.xpath(xpath));
		buttonElement.click();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 5");

		//Level 5
		xpath = "//a[contains(text(),'Enlace!')]";
		WebElement linkElement = driver.findElement(By.xpath(xpath));
		linkElement.click();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 6");

		//Level 6
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.getElementById('hidden\"').click();");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300, 1));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

		//Level 7
		wait = new WebDriverWait(driver, Duration.ofSeconds(300, 1));
		wait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		alert.sendKeys("9");
		alert.accept();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 9");
		
		//Level 8: La solución del Level 8 ya está incluida en el Level 7.

		//Level 9
		String parentWindowHandler = driver.getWindowHandle(); // Almacena tu ventana actual.
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // Obtén todas las ventanas abiertas.
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext())
		{
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // Cámbiate a la última ventana (tu pop-up)
		
		WebElement passElement = driver.findElement(By.id("pass"));
		String passText = passElement.getText();
		driver.close();
		driver.switchTo().window(parentWindowHandler);
		
		textBoxElement = driver.findElement(By.id("input"));
		textBoxElement.sendKeys(passText);
		continueButtonElement = driver.findElement(By.id("next"));
		continueButtonElement.click();
		levelTitleElement = driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(), "Level 10");
		
		//Level 10
		WebElement elementoElement = driver.findElement(By.id("source"));
		WebElement contenedorElement = driver.findElement(By.id("target"));
		Actions act = new Actions(driver);
		act.dragAndDrop(elementoElement, contenedorElement).build().perform();
		xpath = "//h1[contains(text(),'¡Enhorabuena! Has llegado al final de la práctica')]";
		levelTitleElement = driver.findElement(By.xpath(xpath));
		assertEquals(levelTitleElement.getText(), "¡Enhorabuena! Has llegado al final de la práctica");
		
	}

}
