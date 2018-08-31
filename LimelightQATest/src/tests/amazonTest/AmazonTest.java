package tests.amazonTest;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

import models.Product;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.ProductDetailPage;

import selenium.DriverSetup;


public class AmazonTest {
	
	private static WebDriver driver = null;
	private static WebElement element = null;	
	private static List<WebElement> elementTmp;
	private static List<Product> products = new ArrayList<>();
	private static Product product = null;

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
		// firstFive = SearchResultsPage.firstFive(driver);
		String title = "";
		String price = "";
		String stars = "";
		try {						
			for (int i = 0; i < 5; i++) {
				Thread.sleep(5000);			
				SearchResultsPage.productCase(driver, i).click();									
				Thread.sleep(8000);
				title = ProductDetailPage.titleProduct(driver).getText();
				elementTmp = ProductDetailPage.priceProduct(driver);
				if (elementTmp.size() > 0) {
					price = elementTmp.get(0).getText();
				} else {
					price = "0.0";
				}
				elementTmp = ProductDetailPage.starsProduct(driver);
				if (elementTmp.size() > 0) {
					stars = elementTmp.get(0).getAttribute("title");
				} else {
					stars = "0.0";
				}
				System.out.println("Product: " + title);
				System.out.println("Price: " + price);
				System.out.println("stars: " + stars);
				double iPrice = Double.parseDouble(price.substring(1));
				double iStars = Double.parseDouble(stars.substring(0, 3));
				product = new Product(title, iPrice, iStars);
				products.add(product);
				
				if (iPrice > 49 && iPrice < 101) {
					System.out.println("The price is in the range");
				}
				ProductDetailPage.backToListLink(driver).click();				
			}			
			Thread.sleep(5000);
			// Order by price
			Collections.sort(products, new SortByPrice());
			for (int j=0; j < products.size(); j++) {
				System.out.println(products.get(j).price);
			}
			// Order by stars
			Collections.sort(products, new SortByStars());
			for (int j=0; j < products.size(); j++) {
				System.out.println(products.get(j).stars);
			}
			Collections.sort(products, new SortByPrice());
			Product prodTmp = null;
			for (int j=0; j < products.size(); j++) {
				// System.out.println(products.get(j).price);
				if (prodTmp == null) {
					prodTmp = products.get(j);
					continue;
				}
				int resultCompare = Double.compare(products.get(j).price, prodTmp.price);
				boolean orderCorrect = false;
				if (resultCompare == 0 || resultCompare > 0) {
					orderCorrect = true;
				}				
				assertTrue("Order correct", orderCorrect);
				assertFalse("Order incorrect", orderCorrect);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("D");
			e.printStackTrace();
		}
			
	}

	static class SortByPrice implements Comparator<Product>
	{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(Product a, Product b)
	    {
	        return Double.compare(a.price, b.price);
	    }
	}
	static class SortByStars implements Comparator<Product>
	{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(Product a, Product b)
	    {
	    	return Double.compare(a.stars, b.stars);	        
	    }
	}
}
