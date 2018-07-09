package g1;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import org.j

public class SeleniumAdvantageTest {
	
	static WebDriver driver;
    static Wait<WebDriver> wait;

	//Declaring elements for tests
    //Elements for registration test
	WebElement userBtn;
	WebElement createNewAccountBtn;
	WebElement username;
	WebElement usernameField;
	WebElement email;
	WebElement emailField;
	WebElement password;
	WebElement passwordField;
	WebElement confirmPass;
	WebElement confirmPassField;
	
	WebElement iAgreeCheckBox;
	WebElement registerBtn;
	WebElement verification;
	
	
	//Elements for purchase test
	WebElement tablets;
	WebElement tabletToPurchse;
	WebElement addQuantity;
	WebElement addToCartBtn;
	WebElement checkoutBtn;
	WebElement purchaseUsername;
	WebElement purchaseUsernameField;
	WebElement purchasePassword;
	WebElement purchasePasswordField;
	WebElement loginBtn;
	WebElement shippingNextBtn;
	WebElement safepayUsername;
	WebElement safepayUernameField;
	WebElement safepayPassword;
	WebElement safepayPasswordField;
	WebElement buyNowBtn;
	
	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(100);
	
	
	
	@Before
	public void beforeClass(){
		 driver = new FirefoxDriver();
		 wait = new WebDriverWait(driver, 30);
		 
	}
	
	@After
	public void afterClass(){
		driver.quit();	
	}
	
	
	public void findElements(){
		userBtn = driver.findElement(By.xpath("/html/body/header/nav/ul/li[2]/a/a"));
		createNewAccountBtn = driver.findElement(By.className("create-new-account ng-scope")); 
		username = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(2) > sec-view:nth-child(1) > div > label"));
		usernameField = driver.findElement(By.className("ng-pristine ng-valid ng-scope ng-touched in-focus"));
		email = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(2) > sec-view:nth-child(2) > div > label"));
		emailField = driver.findElement(By.className("ng-pristine ng-valid ng-scope ng-touched in-focus"));
		password = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(3) > sec-view:nth-child(1) > div > label"));
		passwordField = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(3) > sec-view:nth-child(1) > div > label"));
		confirmPass = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(3) > sec-view:nth-child(2) > div > label"));
		confirmPassField = driver.findElement(By.cssSelector("div#formCover > div:nth-child(1) > div:nth-child(3) > sec-view:nth-child(2) > div > input"));
		iAgreeCheckBox = driver.findElement(By.className("checkboxText roboto-light animated"));
		registerBtn = driver.findElement(By.cssSelector("section#registerPage > article > sec-form > div:nth-child(2) > sec-sender > a"));
		//verification = driver.findElement(By.);
		tablets = driver.findElement(By.xpath("//*[@id='TabletsImg']"));
		tabletToPurchse = driver.findElement(By.cssSelector("html > body > div:nth-child(8) > section > article > div:nth-child(4) > div > div > div:nth-child(2) > ul > li:nth-child(1) > img"));
		addQuantity = driver.findElement(By.className("plus"));
		addToCartBtn = driver.findElement(By.xpath("//*[@id='productProperties']/div[3]/button"));
		checkoutBtn = driver.findElement(By.cssSelector("tool-tip-cart#toolTipCart > div > table > tfoot > tr:nth-child(2) > td > button"));
		purchaseUsername = driver.findElement(By.cssSelector("div#orderPayment > div:nth-child(1) > div > div:nth-child(1) > sec-form > sec-view:nth-child(1) > div > label"));
		purchaseUsernameField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[1]/div/input"));
		purchasePassword = driver.findElement(By.cssSelector("div#orderPayment > div:nth-child(1) > div > div:nth-child(1) > sec-form > sec-view:nth-child(2) > div > label"));
		purchasePasswordField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[2]/div/input"));
		loginBtn = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-sender/a"));
		shippingNextBtn = driver.findElement(By.xpath("//*[@id='userSection']/div[1]/div[3]/button"));
		safepayUsername = driver.findElement(By.xpath("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[1]/div/label"));
		safepayUernameField = driver.findElement(By.cssSelector("div#paymentMethod > div > div:nth-child(3) > sec-form > sec-view:nth-child(1) > div > input"));
		safepayPassword = driver.findElement(By.cssSelector("div#paymentMethod > div > div:nth-child(3) > sec-form > sec-view:nth-child(2) > div > label"));
		safepayPasswordField = driver.findElement(By.xpath("//*[@id='paymentMethod']/div/div[2]/sec-form/sec-view[2]/div/label"));
		buyNowBtn = driver.findElement(By.xpath("//*[@id='paymentMethod']/div/div[2]/sec-form/div[2]/label/sec-sender/a"));
		
	}
	
	@Test
	public void aRegistrationTest() throws Exception{
		findElements();
		
		//navigation
		driver.get("http://www.advantageonlineshopping.com");
		//Selenium way to wait for the navigation to happen
		driver.wait(10);
		
		userBtn.click();
		createNewAccountBtn.click();
		
		//Filling the form
		username.click();
		usernameField.sendKeys("LFT" + randomInt);
		email.click();
		emailField.sendKeys("LFT" + randomInt + "@AOS.com");
		password.click();
		passwordField.sendKeys("Password1");
		confirmPass.click();
		confirmPassField.sendKeys("Password1");
		
		iAgreeCheckBox.click();
		//Click on Register
		registerBtn.click();
		
		//Verifying the registration is complete by identifying an object on the success page 
		Assert.assertTrue(verification.isDisplayed());
			
	}
	
	@Test
	public void bPurchaseTest() throws Exception{
		findElements();
		
		/*The difference between these two methods comes not from their behavior, but from the behavior in the way the application works and how browser deal with it.
		  navigate().to() navigates to the page by changing the URL like doing forward/backward navigation.
		  get() refreshes the page to changing the URL.*/
		driver.navigate().to("http://www.advantageonlineshopping.com");
		driver.wait(10);
		
		tablets.click();
		tabletToPurchse.click();
		addQuantity.click();
		addToCartBtn.click();
		checkoutBtn.click();
		
		purchaseUsername.click();
		purchaseUsernameField.sendKeys("LFT" + randomInt);
		purchasePassword.click();
		purchasePasswordField.sendKeys("Password1");
		
		loginBtn.click();
		
		safepayUsername.click();
		safepayUernameField.sendKeys("SafePayU");
		safepayPassword.click();
		safepayPasswordField.sendKeys("Password1");
		
		buyNowBtn.click();
		
	}

}
