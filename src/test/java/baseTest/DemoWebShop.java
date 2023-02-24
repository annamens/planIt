package baseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import baseBrowser.BaseBrowserChrome;
import basePackage.HomePage;

public class DemoWebShop extends BaseBrowserChrome {

	@Test
	public void placeOrder() throws InterruptedException, IOException {
		FileReader reader = new FileReader(System.getProperty("user.dir") + "/config/test.properties");
		Properties props = new Properties();
		props.load(reader);

		String expTitle = "Demo Web Shop";
		String expID = "planittest78@gmail.com";
		String user=props.getProperty("username");
		String pass=props.getProperty("password");

		String billingAddress = "planit test, madhapur, Hyderabad 500017, India";

		driver.get(props.getProperty("URL"));
		HomePage homepage = new HomePage(driver);
		homepage.logIn(user, pass);
		assertEquals(driver.getTitle(), expTitle);
		assertEquals(homepage.getUserID(), expID);
		homepage.clearCart(driver);
		homepage.mouseHoverAndSelectDeskTop();
		homepage.selectProduct();
		System.out.println("Price of the item selected is:" + homepage.getProductPrice());
		homepage.enterQuantity(2);
		
		homepage.addTocart();
		assertTrue(homepage.isProductAdded());
		System.out.println("Product added to the cart");
		homepage.clickshoppingList();
		assertEquals(homepage.getSubTotal(), 5060.0);
		homepage.clickCheckbox();
		homepage.clickcheckOut();
		homepage.selectBillingAddress(billingAddress);
		System.out.println("Billing address selected");
		homepage.selectShippingAddress(billingAddress);
		homepage.shippingMethod();
		homepage.choosePaymentMethod();
		assertEquals(homepage.validateMessage(), "You will pay by COD");
		homepage.paymentInfo();
		homepage.confirmOrder();
		assertEquals(homepage.validateOrderText(), "Your order has been successfully processed!");
		homepage.orderPlacedContinue();
		homepage.clickLogout();
		System.out.println("Order placed successfully");

	}
}