package tests.amazonTest;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SearchResultsPage;

import selenium.DriverSetup;


public class AmazonTest {
	
	private static WebDriver driver = null;

	@BeforeClass(alwaysRun = true)
	public void setupClass()
	{
	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupTest()
	{

	}

	@Parameters()
	@Test(description = "Ipad air 2 case search")
	public void groupSetup() throws Exception{
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownTest()
	{

	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass()
	{

	}
	
	public static void main(String[] args) {		
		driver = DriverSetup.setupDriver(DriverSetup.Browser.Chrome, "chromedriver");		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
		HomePage.inputSearch(driver).sendKeys("ipad air 2 case");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage.buttonSearch(driver).click();
		// No existen tienes que aplicar el dropdown
		SearchResultsPage.caseFilter(driver).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		SearchResultsPage.priceFilter(driver).click();
	}
}
