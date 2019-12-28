package keybordsEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import FrameWork.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyTextUsingKeywordEvent extends Constants
{
	@BeforeSuite
	 public void loadConfigFile() throws IOException {
	     FileInputStream file = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\java\\config.properties");
	     configProperties = new Properties();
	     configProperties.load(file);
	     //System.out.println(configProperties.getProperty("appUrl"));
	     // To check whether Url fetched or not from Properties files///
	 }

	 
	 
	 @BeforeTest
	 public void driverSetup()
	 {
	     if(configProperties.getProperty("browser").equals("chrome"))
	     {
	         WebDriverManager.chromedriver().setup();
	         driver=new ChromeDriver();
	     }
	     else if (configProperties.getProperty("browser").equals("firefox"))
	     {
	         WebDriverManager.firefoxdriver().setup();
	         driver= new FirefoxDriver();
	     }
	     else if (configProperties.getProperty("browser").equals("internetExplorer"))
	     { WebDriverManager.iedriver().setup();
	       driver= new InternetExplorerDriver();
	      }
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Integer.parseInt(configProperties.getProperty("implicitWait")), TimeUnit.SECONDS);
	 }

	 @Test(priority = 1)
	 public void landingPageTest() throws InterruptedException {

		String URL = "https://demoqa.com/keyboard-events-sample-form/";
		// Open the browser
		driver.get(URL);
		// It is always advisable to Maximize the window before performing DragNDrop
		// action
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);

		// String sortableText =
		// driver.findElement(By.xpath("//a[text()='Sortable']")).getText();
		   Actions act = new Actions(driver);
		   WebElement copyLocator = driver.findElement(By.xpath("//h3[text()='Interactions']"));
		   act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		   act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		   String text = copyLocator.getText();
		   System.out.println(text);
		   WebElement Name = driver.findElement(By.xpath("//input[@id='userName']"));
		   act.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
		// Locator of Username/////////////// 
		
		
	//	WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
		
		//act.keyDown(sortableText).sendKeys("a").keyUp(Keys.CONTROL).perform();
//		act.keyDown(copyLocator,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
////		act.keyDown(copyLocator, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
//		act.keyDown(Name, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
//		
	 }

}
