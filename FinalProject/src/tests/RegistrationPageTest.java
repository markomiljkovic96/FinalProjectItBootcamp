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
import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class RegistrationPageTest {
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
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	@Test
	public void RegistrationTest() throws IOException, InterruptedException {
		ExcelUtils.generateRandomIdForFirstColumn();
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(this.locators.getProperty("url_menu"));
		ExcelUtils.setExcell(this.locators.getProperty("data_source")); // data_source path to data
		ExcelUtils.setWorkSheet(1);
		SignInPage sip = new SignInPage(driver, locators, waiter);
		RegistrationPage rp = new RegistrationPage(driver, locators, waiter);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			sip.getSignInBtn().click();
			sip.getRegisterBtn().click();

			rp.fillForm(ExcelUtils.getDataAt(i, 0), ExcelUtils.getDataAt(i, 1), ExcelUtils.getDataAt(i, 1),
					ExcelUtils.getDataAt(i, 2), ExcelUtils.getDataAt(i, 3), ExcelUtils.getDataAt(i, 4),
					ExcelUtils.getDataAt(i, 5), ExcelUtils.getDataAt(i, 6), ExcelUtils.getDataAt(i, 7),
					ExcelUtils.getDataAt(i, 8), ExcelUtils.getDataAt(i, 9), ExcelUtils.getDataAt(i, 10),
					ExcelUtils.getDataAt(i, 11));
			rp.fillFormFavorite(i);
			sa.assertEquals(rp.isRegistred(), rp.isExpected(i));

			driver.navigate().to(this.locators.getProperty("url_menu"));
		}
		sa.assertAll();
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
