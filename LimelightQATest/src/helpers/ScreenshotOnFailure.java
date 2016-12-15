package helpers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import selenium.DriverFactory;


public class ScreenshotOnFailure extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		WebDriver driver = DriverFactory.getInstance().getDriver();
		boolean hasQuit = driver.toString().contains("(null)");
		if(!hasQuit){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String NewFileNamePath = null;
			File directory = new File(".");
			Date date = new Date();
			String classAndMethodName = date.getTime() + tr.getMethod().getMethodName();
			try {
				NewFileNamePath =directory.getCanonicalPath() + "\\target\\surefire-reports\\html\\Screenshots\\"+classAndMethodName +".png";
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				FileUtils.copyFile(scrFile, new File(NewFileNamePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String reportFilePath = ".\\Screenshots\\"+classAndMethodName +".png";
			System.setProperty("org.uncommons.reportng.escape-output", "false");	
			Reporter.log("<a href=" + reportFilePath + ">Click to open screenshot</a><img src=" + reportFilePath +  " height='350' width='700'>");
			Reporter.log(date.toString());
	
		}
	}
}