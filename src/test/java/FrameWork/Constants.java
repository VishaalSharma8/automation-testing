package FrameWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class Constants {


    public static WebDriver driver = null;
    
    Properties configProperties;
    SoftAssert assertion;
    WebDriverWait wait;
    String homePageLocation;
    String searchPageLocation;
    String bedNameOnDetails;
    String priceAmountOnSearch;
    String bedNameOnSearch;
    String amountTextOnDetails;
    String propertyLocationOnDetails;
    String checkLogoTextOnSearch;
    String propertyLocationOnSearch;
    String agentNameOnSearch;
    String logoTextOnDetails;
    String agentNameOnDetails;
    String agentNumberOnSearch;
    String agentNumberOnDetails;
    Properties testDataProperties;


}
