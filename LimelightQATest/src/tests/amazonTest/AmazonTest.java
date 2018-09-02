package tests.amazonTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	String title = "";
	String price = "";
	String stars = "";
	
	@Before
	public void setupDriver () {		
		// Step 1
		driver = DriverSetup.setupDriver(DriverSetup.Browser.Chrome, "chromedriver");		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
	}

	@Test
	public void ipadSearch() throws Exception{		
		// Step 2
		HomePage.inputSearch(driver).sendKeys("ipad air 2 case");		
		HomePage.buttonSearch(driver).click();
		// Step 3
		SearchResultsPage.caseFilter(driver).click();
		// Step 4
		SearchResultsPage.priceFilter(driver).click();

		try {						
			for (int i = 0; i < 5; i++) {
				Thread.sleep(3000);			
				SearchResultsPage.productCase(driver, i).click();									
				Thread.sleep(3000);
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
				// Step 5
				System.out.println("Product: " + title);
				System.out.println("Price: " + price);
				System.out.println("stars: " + stars);
				double iPrice = Double.parseDouble(price.substring(1));
				double iStars = Double.parseDouble(stars.substring(0, 3));
				product = new Product(title, iPrice, iStars);
				products.add(product);
				// Step 6
				if (iPrice > 49 && iPrice < 101) {
					System.out.println("The price is in the range");
				}
				ProductDetailPage.backToListLink(driver).click();				
			}			
			Thread.sleep(3000);
			// Step 7
			Collections.sort(products, new SortByPrice());
			for (int j=0; j < products.size(); j++) {
				System.out.println(products.get(j).price);
			}
			// Step 8
			Collections.sort(products, new SortByStars());
			for (int j=0; j < products.size(); j++) {
				System.out.println(products.get(j).stars);
			}
			
			Collections.sort(products, new SortByPrice());			
			Product prodTmp = null;			
			for (int j=0; j < products.size(); j++) {
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
			}
			
			Collections.sort(products, new SortByStars());
			int pursacheChoise = Double.compare(products.get(4).price, products.get(3).price);
			if (pursacheChoise <= 0) {
				System.out.println("Buy the: " + products.get(3).title);
			} else {
				System.out.println("Buy the: " + products.get(4).title);
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
