package FrameWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FrameWorkPart1 extends Constants
{

    @BeforeSuite
    public void loadConfigFile() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\java\\config.properties");
        configProperties = new Properties();
        configProperties.load(file);
        System.out.println(configProperties.getProperty("appUrl"));
        // To check whether Url fetched or not from Properties files///
    }

    @BeforeClass
    public void testDataFile() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\TestData.properties");
        testDataProperties = new Properties();
        testDataProperties.load(file);
        System.out.println(testDataProperties.getProperty("locationCity"));
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
    public void landingPageTest() {
        driver.get(configProperties.getProperty("appUrl"));
        driver.findElement(By.xpath("//input[@id='search-input-location']")).sendKeys(testDataProperties.getProperty("locationCity"));
        String text = driver.findElement(By.xpath("//input[@id='search-input-location']")).getText();
        System.out.println(text);
        selectLocation(testDataProperties.getProperty("locationCity"));
        
        //////////////        lOGGER         ///////////////////
        log = Logger.getLogger(FrameWorkPart1.class);
        log.info("Click on the Submit button on laanding page");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();
    }
    public void selectLocation(String location) {
        wait = new WebDriverWait(driver, Integer.parseInt(configProperties.getProperty("explicitWait")));
        System.out.println("//ul[contains(@class,'ui-widget-content')]//li[@data-value='" + location + "']");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class,'ui-widget-content')]//li[@data-value='" + location + "']")));
        homePageLocation = driver.findElement(By.xpath("//ul[contains(@class,'ui-widget-content')]//li[@data-value='" + location + "']")).getText();
        System.out.println("Home Page Location" + homePageLocation);
        driver.findElement(By.xpath("//ul[contains(@class,'ui-widget-content')]//li[@data-value='" + location + "']")).click();
    }
     @Test(priority = 2)
     public void validateSearchPage()
     {
         assertion = new SoftAssert();
        String actualUrl = driver.getCurrentUrl();
        assertion.assertTrue(actualUrl.contains("for-sale/property"), "Url is mis-matched");
        searchPageLocation = driver.findElement(By.xpath("//span[@class='search-refine-location-text']")).getText();
        System.out.println("location Of Search Page" + searchPageLocation);
        assertion.assertEquals(homePageLocation, searchPageLocation);
         //////////  JavaScriptExecutor /////////////////// To Scroll upto particular element/////////
         log.info("Scrolliing upto a price tag-index=4 under Validate Search page");
         WebElement listOfElements = driver.findElements(By.xpath("//ul[@data-role='listview']//div[@class='listing-results-wrapper']//a[contains(@class,'text-price')]")).get(4);
         Actions actions = new Actions(driver);
         actions.moveToElement(listOfElements).perform();

//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("arguments[0].scrollIntoView();",listOfElements);
        List<WebElement> bedList = driver.findElements(By.xpath("//ul[@data-role='listview']//div[@class='listing-results-wrapper']//h2//a"));
        List<WebElement> propertyLocation = driver.findElements(By.xpath("//div[contains(@class,'results-right')]//span//a[contains(@class,'results-address')]"));
        List<WebElement> agentLogo = driver.findElements(By.xpath("//div[@class='agent_logo']//img"));
        List<WebElement> agentName = driver.findElements(By.xpath("//div[@class='listing-results-wrapper']//p[contains(@class,'listing-results')]//span"));
        List<WebElement> agentNumber = driver.findElements(By.xpath("//div[@class='listing-results-right']//span[@class='agent_phone']//a//span"));
        List<WebElement> priceList = driver.findElements(By.xpath("//ul[@data-role='listview']//div[@class='listing-results-wrapper']//a[contains(@class,'text-price')]"));

        agentNumberOnSearch = agentNumber.get(4).getText();
        System.out.println(agentNumberOnSearch);
        agentNameOnSearch = agentName.get(4).getText();
        System.out.println("Agent Name on Search Page" + agentNameOnSearch);
        checkLogoTextOnSearch = agentLogo.get(4).getAttribute(testDataProperties.getProperty("attribute"));
        System.out.println("logo Text On Search Page" + checkLogoTextOnSearch);
        propertyLocationOnSearch = propertyLocation.get(4).getText();
        System.out.println("search page location" + propertyLocationOnSearch);
        wait =new WebDriverWait(driver ,Integer.parseInt(configProperties.getProperty("explicitWait")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@data-role='listview']//div[@class='listing-results-wrapper']//h2//a")));
        bedNameOnSearch = bedList.get(4).getText();
        System.out.println(bedNameOnSearch);
         wait = new WebDriverWait(driver,Integer.parseInt(configProperties.getProperty("explicitWait")));
         wait.until(ExpectedConditions.elementToBeClickable(priceList.get(4)));
        priceAmountOnSearch = priceList.get(4).getText();
        priceList.get(4).click();
        System.out.println(priceAmountOnSearch);
         assertion.assertAll();
    }
    @Test(priority = 3)
    public void saleDetails()
    {
        String actualUrl = driver.getCurrentUrl();
        assertion.assertTrue(actualUrl.contains("for-sale/details/"));
        bedNameOnDetails = driver.findElement(By.xpath("//article[contains(@class,'wrapper__summary')]//h1")).getText();
        System.out.println("Bed Name On Details " + bedNameOnDetails);
        amountTextOnDetails = driver.findElement(By.xpath("//div[@id='dp-sticky-element']//div[@class='ui-pricing']//p")).getText();
        propertyLocationOnDetails = driver.findElement(By.xpath("//article[contains(@class,'wrapper__summary')]//h2")).getText();
        System.out.println("Details page location" + propertyLocationOnDetails);
        agentNameOnDetails = driver.findElement(By.xpath("//div[@id='dp-sticky-element']//div[@class='ui-agent__text']//h4")).getText();
        System.out.println("Agent Name On Details" + agentNameOnDetails);
        logoTextOnDetails = driver.findElement(By.xpath("//div[@id='dp-sticky-element']//div[@class='ui-agent__logo']//img")).getAttribute(testDataProperties.getProperty("attribute"));
        agentNumberOnDetails = driver.findElement(By.xpath("//div[@id='dp-sticky-element']//p[contains(@class,'tel ui-agent')]//a")).getText();
        WebElement plotName = driver.findElement(By.xpath("//div[@id='dp-sticky-element']//div[@class='ui-agent__text']//h4"));
        plotName.click();
        System.out.println("Agent Number On Details " + agentNumberOnDetails);
        System.out.println("Logo Text on Details" + logoTextOnDetails); // Use for remove character//
        System.out.println("Amount Text On Details Page " + amountTextOnDetails);
        assertion.assertEquals(bedNameOnSearch, bedNameOnDetails);
        assertion.assertTrue(priceAmountOnSearch.contains(amountTextOnDetails), "Amount are mis-matched");
        assertion.assertEquals(propertyLocationOnDetails, propertyLocationOnSearch);
        assertion.assertTrue(agentNameOnSearch.contains(agentNameOnDetails), "agents name are mismatched");
        assertion.assertTrue(checkLogoTextOnSearch.contains(logoTextOnDetails), "logo texts are different");
        assertion.assertTrue(agentNumberOnDetails.contains(agentNumberOnSearch.substring(4,15)),"Agent Number is mis-matched ");
        assertion.assertAll();
    }
    @Test(priority = 4)
    public void agentPage() {
        String actualUrl = driver.getCurrentUrl();
        assertion.assertTrue(actualUrl.contains("find-agents"), "Url is Mismatched");
        WebElement findAgents = driver.findElement(By.xpath("//li[@id='mn-agents']"));
        Actions act = new Actions(driver);
        act.moveToElement(findAgents).perform();
        boolean isFindOutButtonDisplayed = driver.findElement(By.xpath("//a[contains(@data-ga-action,'agents_Find')]")).isDisplayed();
        assertion.assertTrue(isFindOutButtonDisplayed);
        boolean isLoginButtonDisplayed = driver.findElement(By.xpath("//a[contains(@data-ga-action,'agents_Login')]")).isDisplayed();
        if (isLoginButtonDisplayed) {
            driver.findElement(By.xpath("//a[contains(@data-ga-action,'agents_Login')]")).click();
        } else {
            System.out.println("Button is not displayed");
        }
   
                
    }
}