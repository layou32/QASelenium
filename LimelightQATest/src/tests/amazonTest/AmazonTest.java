package tests.amazonTest;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	private static WebElement element = null;	

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
		HomePage.inputSearch(driver).sendKeys("ipad air 2 case");		
		HomePage.buttonSearch(driver).click();
		// No existen tienes que aplicar el dropdown
		SearchResultsPage.caseFilter(driver).click();		
		SearchResultsPage.priceFilter(driver).click();
		
		if (SearchResultsPage.firstFive(driver).size() > 0) {			
			for (int i = 0; i < 5; i++) {				
				String name = "";
				String price = "";
				String stars = "";
				
				element = SearchResultsPage.firstFive(driver).get(i);							
				
				name = "Name: " + SearchResultsPage.titleProduct(element).getAttribute("data-attribute");
				
				boolean breakIt = true;
		        while (true) {
			        breakIt = true;
			        try {
			        	price = "Price: " + element.findElement(By.cssSelector("span.sx-price-whole")).getText();
			        } catch (Exception e) {
			            if (e.getMessage().contains("element is not attached")) {
			                breakIt = false;
			            }
			        }
			        if (breakIt) {
			            break;
			        }
			    }
											
				// name = "Name: " + element.findElement(By.cssSelector("h2.s-access-title")).getAttribute("data-attribute");
				// price = "Price: " + element.findElement(By.cssSelector("span.sx-price.sx-price-large")).getText();				
				// stars = "Star's: " + element.findElement(By.cssSelector("i.a-icon-star span")).getText(); 
				
				System.out.println(name);
				System.out.println(price);
				//System.out.println(stars);
			}
		} else {
			System.out.println("Nada de nada");
		}		
	}
}
