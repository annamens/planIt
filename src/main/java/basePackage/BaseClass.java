package basePackage;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected int staticNavBarHeight = 140;
	protected long sleepInMillis = 500;
	static WebDriver driver;

	public WebElement scrollTo(WebElement el, WebDriver driver) {
		Point location = el.getLocation();
		Dimension size = el.getSize();
		int y = location.getY() - (size.getHeight() / 2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(String.format("window.scrollTo (0, %s)",
				y <= staticNavBarHeight ? staticNavBarHeight : y - staticNavBarHeight));
		return el;
	}

	public void click(WebElement el) {
//		scrollTo(By(el),driver);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(el));
		el.click();
//		return true;
	}

	public void executeScriptClick(WebElement button) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
	}

	public void setText(WebElement el, String text) {
		el.sendKeys(text);

	}

	public void setTextAndEnter(WebElement el, String text) {
		el.sendKeys(text, Keys.ENTER);

	}

	public void hover(WebElement el, WebDriver driver) {
//		scrollTo(el, driver);
		new Actions(driver).moveToElement(el).build().perform();
	}
}
