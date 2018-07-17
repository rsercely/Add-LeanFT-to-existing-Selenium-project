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

//    static boolean createAndUseNewUser = true;
  private static final boolean createAndUseNewUser = false;

    @Test
    public void runTests() throws Exception {
        if (createAndUseNewUser) {
            RegistrationTest();
        }
        PurchaseTest();
    }

    private static WebDriver driver;
    private static WebDriverWait wait;
    //Declaring elements for tests
    //Elements for registration test
	private WebElement userBtn;
	private WebElement createNewAccountBtn;
	private WebElement usernameField;
	private WebElement emailField;
	private WebElement passwordField;
	private WebElement confirmPassField;
	
	private WebElement iAgreeCheckBox;
	private WebElement registerBtn;
	// --Commented out by Inspection (7/17/2018 11:52 AM):WebElement whoLoggedIn;
	
	//Elements for purchase test
	private WebElement tablets;
	private WebElement tabletToPurchase;
	private WebElement addQuantity;
	private WebElement addToCartBtn;
	private WebElement openShoppingCart;
	private WebElement checkoutBtn;
	private WebElement purchaseUsernameField;
	private WebElement purchasePasswordField;
	private WebElement loginBtn;
	private WebElement shippingNextBtn;
	private WebElement safepayUsernameField;
	private WebElement safepayPasswordField;
	private WebElement payNowBtn;
	
	private final Random randomGenerator = new Random();
	private int randomInt = randomGenerator.nextInt(100);

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


	private void findNewAccountElements() {
        usernameField = driver.findElement(By.name("usernameRegisterPage"));
		emailField = driver.findElement(By.name("emailRegisterPage"));
        passwordField = driver.findElement(By.name("passwordRegisterPage"));
        confirmPassField = driver.findElement(By.name("confirm_passwordRegisterPage"));
        iAgreeCheckBox = driver.findElement(By.name("i_agree"));
        registerBtn = driver.findElement(By.id("register_btnundefined"));
	}

	private void RegistrationTest() throws Exception{
		//navigation
		driver.get("http://www.advantageonlineshopping.com");

		// open users page - takes a while so wait until it is clickable
        userBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav/ul/li[3]/a/a")));
// or this
//      userBtn = driver.findElement(By.xpath("//*[@id='menuUser']"));
        userBtn.click();

        // createNewAccountBtn = driver.findElement(By.className("create-new-account.ng-scope"));
        // above fails. Selenium sees classNames with a space in them as a "compound selecter" and fails
        createNewAccountBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/login-modal/div/div/div[3]/a[2]")));
        Thread.sleep(1*1000); // even with the above, need to sleep a bit
        createNewAccountBtn.click();
        // now the Create Account window is open
        // learn the new accounts page fields
        findNewAccountElements();

        // create the account
        if (randomInt<10) { // username must be at least 5 characters, so pad if necessary
            randomInt +=10;
        }
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
        WebElement whoLoggedIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav/ul/li[3]/a/span")));
        String loginName = whoLoggedIn.getText();
        System.out.println(loginName + " was created and is currently logged in");
        Assert.assertEquals("LFT" + randomInt, loginName);
        //*[@id="loginMobileMiniTitle"]/label[3]

        WebElement logoutButton = driver.findElement(By.xpath("//div/label[text()='Sign out']"));
        whoLoggedIn.click();
//        logoutButton.click();
        System.out.println("success");
	}

	private void PurchaseTest() throws Exception{

		driver.get("http://www.advantageonlineshopping.com");
		tablets = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabletsImg']")));
        tablets.click();
        tabletToPurchase = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html > body > div:nth-child(8) > section > article > div:nth-child(4) > div > div > div:nth-child(2) > ul > li:nth-child(1) > p:nth-child(4) > a")));
        Thread.sleep(1*1000);
        tabletToPurchase.click();
        addQuantity = driver.findElement(By.className("plus"));
        addQuantity.click();
        addToCartBtn = driver.findElement(By.xpath("//*[@id='productProperties']/div[3]/button"));
        addToCartBtn.click();
        openShoppingCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCart")));
        openShoppingCart.click();
        checkoutBtn = driver.findElement(By.cssSelector("tool-tip-cart#toolTipCart > div > table > tfoot > tr:nth-child(2) > td > button"));
        checkoutBtn.click();

		purchaseUsernameField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[1]/div/input"));
		purchasePasswordField = driver.findElement(By.xpath("//*[@id='orderPayment']/div[1]/div/div[1]/sec-form/sec-view[2]/div/input"));
		loginBtn = driver.findElement(By.id("login_btnundefined"));

		if (createAndUseNewUser) {
            purchaseUsernameField.sendKeys("LFT" + randomInt);
            purchasePasswordField.sendKeys("Password1");
        } else {
			purchaseUsernameField.sendKeys("<your AOS account name here>");
			purchasePasswordField.sendKeys("<your AOS password here>");
        }
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
		Thread.sleep(5*1000);
		Assert.assertTrue(paymentSuccess.isDisplayed());
	}


}

