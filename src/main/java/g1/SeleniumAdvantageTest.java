package g1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.*;

public class SeleniumAdvantageTest {
	
	static WebDriver driver;
    static WebDriverWait wait;
    //Declaring elements for tests
    //Elements for registration test
	WebElement userBtn;
	WebElement createNewAccountBtn;
	WebElement usernameField;
	WebElement emailField;
	WebElement passwordField;
	WebElement confirmPassField;
	
	WebElement iAgreeCheckBox;
	WebElement registerBtn;
	WebElement whoLoggedIn;
	
	
	//Elements for purchase test
	WebElement tablets;
	WebElement tabletToPurchse;
	WebElement addQuantity;
	WebElement addToCartBtn;
	WebElement openShoppingCart;
	WebElement checkoutBtn;
	WebElement purchaseUsernameField;
	WebElement purchasePasswordField;
	WebElement loginBtn;
	WebElement shippingNextBtn;
	WebElement safepayUsername;
	WebElement safepayUsernameField;
	WebElement safepayPasswordField;
	WebElement payNowBtn;
	
	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(100);
	
	
	
	@Before
	public void beforeClass() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        Thread.sleep(10*1000);
    }
	
	@After
	public void afterClass(){
		driver.quit();
	}


	public void findNewAccountElements() throws InterruptedException {
        usernameField = driver.findElement(By.name("usernameRegisterPage"));
		emailField = driver.findElement(By.name("emailRegisterPage"));
        passwordField = driver.findElement(By.name("passwordRegisterPage"));
        confirmPassField = driver.findElement(By.name("confirm_passwordRegisterPage"));
        iAgreeCheckBox = driver.findElement(By.name("i_agree"));
        registerBtn = driver.findElement(By.id("register_btnundefined"));
	}

	@Ignore
	@Test
	public void RegistrationTest() throws Exception{
		//navigation
		driver.get("http://www.advantageonlineshopping.com");
		Thread.sleep(10*1000);

		// open users page
        userBtn = driver.findElement(By.xpath("/html/body/header/nav/ul/li[3]/a/a"));
// or this
//      userBtn = driver.findElement(By.xpath("//*[@id='menuUser']"));
        userBtn.click();

        // createNewAccountBtn = driver.findElement(By.className("create-new-account.ng-scope"));
        // above fails. Selenium sees classNames with a space in them as a "compound selecter" and fails
        createNewAccountBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/login-modal/div/div/div[3]/a[2]")));
        Thread.sleep(1*1000);
        createNewAccountBtn.click();
        // now the Create Account window is open
        // learn the new accounts page fields
        findNewAccountElements();

        // create the account
		usernameField.sendKeys("LFT" + randomInt);
		emailField.sendKeys("LFT" + randomInt + "@AOS.com");
		passwordField.sendKeys("Password1");
		confirmPassField.sendKeys("Password1");
		Thread.sleep(1*1000);
		iAgreeCheckBox.click(); // sometimes this simply doesn't work, so check and retry
		Boolean okToRegister = registerBtn.isEnabled();
		if (! okToRegister) {
			Thread.sleep(1*1000);
			iAgreeCheckBox.click();
			okToRegister = registerBtn.isEnabled();
		}
		Assert.assertTrue(okToRegister);
		registerBtn.click();

		//Verifying the registration is complete by identifying an object on the success page
        Thread.sleep(10*1000);
        WebElement whoLoggedIn = driver.findElement(By.xpath("/html/body/header/nav/ul/li[3]/a/span"));
        String loginName = whoLoggedIn.getText();
        System.out.println(loginName + " was created and is currently logged in");
        Assert.assertEquals("LFT" + randomInt, loginName);
        //*[@id="loginMobileMiniTitle"]/label[3]

        WebElement logoutButton = driver.findElement(By.xpath("//div/label[text()='Sign out']"));
        whoLoggedIn.click();
//        logoutButton.click();
        System.out.println("success");
	}

	@Test
	public void PurchaseTest() throws Exception{

		driver.get("http://www.advantageonlineshopping.com");
		Thread.sleep(10*1000);
        tablets = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabletsImg']")));
        tablets.click();
        tabletToPurchse = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html > body > div:nth-child(8) > section > article > div:nth-child(4) > div > div > div:nth-child(2) > ul > li:nth-child(1) > p:nth-child(4) > a")));
        tabletToPurchse.click();
        addQuantity = driver.findElement(By.className("plus"));
        addQuantity.click();
        addToCartBtn = driver.findElement(By.xpath("//*[@id='productProperties']/div[3]/button"));
        addToCartBtn.click();
        openShoppingCart = driver.findElement(By.id("menuCart"));
        openShoppingCart.click();
        checkoutBtn = driver.findElement(By.cssSelector("tool-tip-cart#toolTipCart > div > table > tfoot > tr:nth-child(2) > td > button"));
        checkoutBtn.click();

		purchaseUsernameField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[1]/div/input"));
		purchasePasswordField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[2]/div/input"));
		loginBtn = driver.findElement(By.id("login_btnundefined"));

/*
		purchaseUsernameField.sendKeys("LFT" + randomInt);
		purchasePasswordField.sendKeys("Password1");
*/
		purchaseUsernameField.sendKeys("rsercely2");
		purchasePasswordField.sendKeys("1Qaz");
		loginBtn.click();

		shippingNextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("next_btn")));
		shippingNextBtn.click();

		safepayUsernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("safepay_username")));
		safepayPasswordField = driver.findElement(By.name("safepay_password"));
		payNowBtn = driver.findElement(By.id("pay_now_btn_SAFEPAY"));

		safepayUsernameField.clear(); // must clear as sendKeys appends to what is already there
		safepayUsernameField.sendKeys("SafePayU");
		safepayPasswordField.clear(); // and eventually the fields become too long
		safepayPasswordField.sendKeys("Password1");
		payNowBtn.click();

		WebElement paymentSuccess = (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orderPaymentSuccess']/h2/span"))));
		Thread.sleep(3*1000);
		Assert.assertTrue(paymentSuccess.isDisplayed());
	}

}

