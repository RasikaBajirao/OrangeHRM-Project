package Listeners;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.listeners.ExtentManager;

import Module1.DataDrivenTest ;

public class ExtentITestNGListeners extends DataDrivenTest implements ITestListener 
{
	ExtentReports extent=ExtentManager.getInstance();
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) 
	{
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
	
		parentTest.set(extentTest);
	}
		
	public void onTestSuccess(ITestResult result) 
	{
		parentTest.get().pass("Test Passed");
		String pass=result.getName();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		captureScreenshot(result.getTestClass().getRealClass().getSimpleName()+"_"+pass+"_"+timestamp()+".png");

	  }
	
	private String timestamp()
	{
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	}

	public void onTestFailure(ITestResult result) 
	{
		parentTest.get().fail("Test Failed "+result.getThrowable().getMessage());
		String fail=result.getName();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		captureScreenshot(result.getTestClass().getRealClass().getSimpleName()+"_"+fail+"_"+timestamp()+".png");
	  }
	
	public void onTestSkipped(ITestResult result) 
	{
		parentTest.get().skip("Test Skipped "+result.getThrowable().getMessage());
	   
	  }
	
	public  void onFinish(ITestContext context) 
	{
		extent.flush();
	    
	  }
	
	
}
