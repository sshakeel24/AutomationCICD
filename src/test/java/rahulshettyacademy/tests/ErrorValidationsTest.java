package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("rahul@rahulshettyacademy.com", "Hirah");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
		// //div[@aria-label='Incorrect email or password.']
		// div[aria-label='Incorrect email or password.'] -could,t catch like rahul 

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "QWERTY";
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("QWERTYASD");
		Assert.assertFalse(match);
		
	
	}

}
