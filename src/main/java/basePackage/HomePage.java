package basePackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseClass {
	WebDriver driver;
	String userName = "planittest78@gmail.com";
	String pass = "123456";

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "#nav-search-submit-button")
	WebElement searchButton;

	@FindBy(css = ".ico-login")
	WebElement loginBtn;

	@FindBy(xpath = "(//*[text()='Sign in'])[1]")
	WebElement signIn;

	@FindBy(css = "#Email")
	WebElement enterUser;

	@FindBy(css = "#Password")
	WebElement enterPass;

	@FindBy(xpath = "//input[@value='Log in']")
	WebElement clickLogin;

	@FindBy(xpath = "//div[@class='header-links']//a[@class='account']")
	WebElement accntID;

	@FindBy(xpath = "//span[text()='Shopping cart']")
	WebElement clickCart;

	@FindBy(css = ".remove-from-cart")
	List<WebElement> removeCartItems;

	@FindBy(xpath = "//input[@name='updatecart']")
	WebElement updateCart;

	@FindBy(xpath = "//h2[@class='product-title']")
	List<WebElement> productList;

	@FindBy(xpath = "//div[@id='payment-info-buttons-container']//input[@value='Continue']")
	WebElement paymentInfo;

	@FindBy(xpath = "//div[@id='confirm-order-buttons-container']//input[@value='Confirm']")
	WebElement confirmOrder;

	@FindBy(xpath = "//div[@class='title']")
	WebElement validateOrderText;

	@FindBy(xpath = "//div[@class='buttons']//*[@value='Continue']")
	WebElement orderPlacedContinue;

	@FindBy(xpath = "//*[text()='Log out']")
	WebElement logout;

	@FindBy(xpath = "//ul[@class='top-menu']//a[@href='/computers']")
	WebElement computers;

	@FindBy(xpath = "//ul[@class='top-menu']//a[@href='/desktops']")
	WebElement desktop;
	
	@FindBy(xpath = "//input[@id='addtocart_16_EnteredQuantity']")
	WebElement enterQuantityField;

	public void enterUsername(WebElement el, String s) {
		setText(el, s);
	}

	public void enterUsername(String s) {
		enterUser.sendKeys(s);
	}

	public void enterPassword(String s) {
		enterPass.sendKeys(s);
	}

	public void logIn(String user, String password) {
		click(loginBtn);
		enterUsername(user);
		enterPassword(password);
		clickLogin.click();
	}

	public String getUserID() {

		return accntID.getText();
	}

	public void clearCart(WebDriver driver) {
		clickCart.click();
		if (!removeCartItems.isEmpty()) {
			for (WebElement el : removeCartItems) {
				el.click();
			}
			updateCart.click();
		}
	}

	public void mouseHoverAndSelectDeskTop() {
		new Actions(driver)
				.moveToElement(driver.findElement(By.xpath("//ul[@class='top-menu']//a[@href='/computers']"))).build()
				.perform();
		driver.findElement(By.xpath("//ul[@class='top-menu']//a[@href='/desktops']")).click();

	}

	@FindBy(css = ".product-price")
	WebElement price;

	public String getProductPrice() {
		String price1 = price.getText();
		return price1;
	}

	public float getSubTotal() {
		String price1 = price.getText();
		String qty = driver.findElement(By.xpath("//td[@class='qty nobr']//input[@type='text']")).getAttribute("value");
		float p = Float.parseFloat(price1);
		float q = Float.parseFloat(qty);
		float subTotal = p * q;
		return subTotal;
	}

	

	public void enterQuantity(int n) {
		String s = String.valueOf(n);
		enterQuantityField.clear();
		enterQuantityField.sendKeys(s);

	}

	public void clickshoppingList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Shopping cart']"))))
				.click();

	}

	@FindBy(xpath = "//input[@id='add-to-cart-button-16']")
	WebElement addTocart;

	public void addTocart() {
		addTocart.click();

	}

	@FindBy(xpath = "//*[text()='The product has been added to your ']")
	WebElement addedSucess;

	public boolean isProductAdded() throws InterruptedException {
		boolean flag = true;
		WebElement e = driver.findElement(By.xpath("//p[text()='Please select HDD']"));
		if (e.isDisplayed()) {
			driver.findElement(By.xpath("//span[@title='Close']")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@id='product_attribute_16_3_6_18']")).click();
			addTocart();
			flag = true;
			Thread.sleep(4000);
		}
//		 WebElement el = driver.findElement(By.xpath("//*[text()='The product has been added to your ']"));
		else if (addedSucess.isDisplayed()) {
			flag = true;
			Thread.sleep(4000);

		} else {
			flag = false;
		}
		return flag;
	}

	@FindBy(xpath = "//div[@class='terms-of-service']//input[@id='termsofservice']")
	WebElement checkBox;

	public void clickCheckbox() {
		checkBox.click();

	}

	@FindBy(xpath = "//button[@id='checkout']")
	WebElement checkOut;

	public void clickcheckOut() {
		checkOut.click();

	}

	public void selectProduct() {
		for (WebElement el : productList) {

			if (el.getText().equals("Build your own computer")) {
				el.click();
				break;

			}
		}

	}

	public void selectBillingAddress(String s) {
		Select select = new Select(driver.findElement(By.xpath("//select[@id='billing-address-select']")));
		select.selectByVisibleText(s);
		driver.findElement(By.xpath("//div[@id='billing-buttons-container']//input[@value='Continue']")).click();
	}

	public void selectShippingAddress(String s) {
		Select select = new Select(driver.findElement(By.xpath("//select[@id='shipping-address-select']")));
		select.selectByVisibleText(s);
		driver.findElement(By.xpath("//div[@id='shipping-buttons-container']//input[@value='Continue']")).click();
	}

	public void shippingMethod() {
		driver.findElement(By.xpath("//input[@id='shippingoption_1']"));
		driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//input[@value='Continue']"))
				.click();
	}

	public void choosePaymentMethod() {
		driver.findElement(By.xpath("//input[@id='paymentmethod_0']")).click();
		driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']//input[@value='Continue']")).click();
	}

	@FindBy(xpath = "//*[text()='You will pay by COD']")
	WebElement validateMessage;

	public String validateMessage() {
		return validateMessage.getText();

	}

	public void paymentInfo() {
		paymentInfo.click();
	}

	public void confirmOrder() {
		confirmOrder.click();
	}

	public String validateOrderText() {
		return validateOrderText.getText();
	}

	public void orderPlacedContinue() {
		orderPlacedContinue.click();
	}

	public void clickLogout() {
		logout.click();
	}

}
