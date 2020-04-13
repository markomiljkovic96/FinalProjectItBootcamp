package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.SignInPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws FileNotFoundException, IOException {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "driver-lib\\msedgedriver.exe");
			this.driver = new EdgeDriver();
		}
		this.locators = new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(this.locators.getProperty("url_menu"));
		
	}

	@Test
	public void addToCartTest() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		SignInPage sip = new SignInPage(driver, locators, waiter);
		StoreItemPage sp = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);
		sip.getSignInBtn().click();
		ExcelUtils.setExcell(this.locators.getProperty("data_source")); // data_source path to data
		ExcelUtils.setWorkSheet(1);
		sip.login(ExcelUtils.getDataAt(1, 0), ExcelUtils.getDataAt(1, 1));
		ExcelUtils.setWorkSheet(0);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			sp.addToCart(ExcelUtils.getDataAt(i, 1));
			sa.assertTrue(cp.isAddedToCart(ExcelUtils.getDataAt(i, 0)));
		}
		sa.assertAll();
	}

	@Test
	public void countCartTest() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell(this.locators.getProperty("data_source")); // data_source path to data
		ExcelUtils.setWorkSheet(0);
		CartPage cp = new CartPage(driver, locators, waiter);
		for (int i = 1; i < ExcelUtils.getRowNumber() - 1; i++) {
			cp.setQuantity(i, ExcelUtils.getDataAtNum(i, 2));
		}
		cp.getUpdateCartBtn().click();
		sa.assertTrue(cp.isEqually());
		sa.assertAll();
	}

	@Test
	public void emptyCartTest() {
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(this.locators.getProperty("url_menu"));
		CartPage cp = new CartPage(driver, locators, waiter);
		StoreItemPage sp = new StoreItemPage(driver, locators, waiter);
		ExcelUtils.setExcell(this.locators.getProperty("data_source")); // data_source path to data
		ExcelUtils.setWorkSheet(0);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			sp.addToCart(ExcelUtils.getDataAt(i, 1));
		}
		driver.navigate().to(this.locators.getProperty("url_fullcart"));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		cp.cartIsEmpty();
		sa.assertTrue(cp.cartIsEmpty());

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}