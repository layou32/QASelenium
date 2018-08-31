package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ElementTraversal;

public class ProductDetailPage {
	private static List<WebElement> elementTmp;
	private static WebElement element = null;
	
	public static WebElement backToListLink(WebDriver driver) { 
		element = driver.findElement(By.id("breadcrumb-back-link"));
		return element;
	}
	
	public static WebElement titleProduct(WebDriver driver) {
		element = driver.findElement(By.id("title"));
		return element;
	}
	
	public static List<WebElement> priceProduct(WebDriver driver) {
		elementTmp = driver.findElements(By.cssSelector("#priceblock_ourprice"));
		return elementTmp;
	}
	
	public static List<WebElement> starsProduct(WebDriver driver) {
		elementTmp = driver.findElements(By.cssSelector("#acrPopover"));
		return elementTmp;
	}
}
