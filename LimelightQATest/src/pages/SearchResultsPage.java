package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	private static WebElement element = null;
	private static List<WebElement> elements = null;
	private static WebDriverWait wait = null;
	
	public static WebElement caseFilter(WebDriver driver) {
		// element = driver.findElement(By.cssSelector("span.a-size-small.a-color-base"));
		element = driver.findElement(By.partialLinkText("Cases"));
		return element;
	}
	
	public static WebElement priceFilter(WebDriver driver) {		
		element = driver.findElement(By.linkText("$50 to $100"));
		return element;
	}
	
	public static List<WebElement> firstFive(WebDriver driver) {		
		elements = driver.findElements(By.cssSelector("li[id^='result_']"));		
		return elements;
	}
	
	public static WebElement titleProduct(WebElement elem) {
		element = elem.findElement(By.cssSelector("h2.s-access-title"));
		return element;
	}
	
	public static WebElement priceProduct(WebElement elem) {		
		return element;
	}
	
	public static WebElement starsProduct(WebElement elem) {
		return element;
	}
}
