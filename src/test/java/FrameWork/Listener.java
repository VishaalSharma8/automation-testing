package FrameWork;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listener extends FrameWorkPart1 implements ITestListener
{
    public void onTestStart(ITestResult result) {System.out.println("Test Started");}

    public void onTestSuccess(ITestResult result) {System.out.println("Test Completed"); }

    public void onTestFailure(ITestResult result) {
       System.out.println("Test case "+ result.getName()+" has failed");
        TakesScreenshot tss = (TakesScreenshot) driver; // Convert WebDriver object to TakeScreeshot.////
        File temporary = tss.getScreenshotAs(OutputType.FILE); //call getScreenshotAs() method to create image File//
        try {
            FileUtils.copyFile(temporary,new File(System.getProperty("user.dir")+"\\src\\test\\java\\Screenshot\\"+result.getName()+" .jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) { }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    public void onTestFailedWithTimeout(ITestResult result) { }

    public void onStart(ITestContext context) { }

    public void onFinish(ITestContext context) { }
}
