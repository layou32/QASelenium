package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebElement element = null;
	
	public static WebElement inputSearch(WebDriver driver) {
		element = driver.findElement(By.id("twotabsearchtextbox"));		
		return element;
	}
	
	public static WebElement buttonSearch(WebDriver driver) {
		element = driver.findElement(By.cssSelector("input.nav-input"));
		return element;
	}
	
}
