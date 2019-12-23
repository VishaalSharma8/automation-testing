package FrameWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoClass
{
    WebDriver driver;
    @Test
    public void scrollTestCase()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       // driver.get("http://demo.guru99.com/test/guru99home/");
       // driver.get("http://demo.guru99.com/test/guru99home/scrolling.html");
        driver.get("http://demo.guru99.com/test/guru99home/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement seleniumText = driver.findElement(By.linkText("SELENIUM"));// scrolled up to a particular element.
        WebElement ethicalHacking = driver.findElement(By.linkText("Ethical Hacking"));
        //js.executeScript("arguments[0].scrollIntoView();", seleniumText); //scrolled up to a particular element.
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // scrolled  to a bottom of page.
     //   js.executeScript("arguments[0].scrollIntoView();", ethicalHacking);// horizontal scrolled
          js.executeScript("window.scrollBy(0,500)");  // scrolled w.r.t y coordinate using axix////

    }

//    @Test
//    public  void method(){
//        String s = "+44 20 3641 9852";
//        String newString = s.substring(4, 15);
//        System.out.println(newString);
//    }



}
