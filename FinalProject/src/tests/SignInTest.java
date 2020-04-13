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

import pages.HomePage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInTest {
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
	}

	@Test
	public void SignInTests() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(this.locators.getProperty("url_menu"));
		ExcelUtils.setExcell(this.locators.getProperty("data_source")); // data_source path to data
		ExcelUtils.setWorkSheet(1);
		SignInPage sip = new SignInPage(driver, locators, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			sip.getSignInBtn().click();
			sip.login(ExcelUtils.getDataAt(i, 0), ExcelUtils.getDataAt(i, 1));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			sa.assertEquals(sip.isSignIn(), sip.isExpected(i));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			sip.getSignoutBtn().click();
		}
		sa.assertAll();

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}

