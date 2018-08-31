package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
	private static WebElement element = null;
	
	public static WebElement caseFilter(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='leftNavContainer']/ul/ul/div/li/span/a/span"));
		return element;
	}
	
	public static WebElement priceFilter(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='leftNavContainer']/ul[17]/div/li[2]/span/a/span"));
		return element;
	}
}
