package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class PetStoreMenuTest {
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

	// TODO provera statusa stranice
	@Test
	public void LinksTest() throws InterruptedException {
		SoftAssert sa = new SoftAssert();

		List<WebElement> links = this.driver.findElements(By.xpath(this.locators.getProperty("left_nav_links")));

		for (int i = 0; i < links.size(); i++) {
			int status = verifyURLStatus(links.get(i).getAttribute("href"));
			sa.assertTrue(status < 400);
		}
		sa.assertAll();
	}
	// TODO Testiranje ispravnosti linkova

	@Test
	public void LinkTests() throws InterruptedException {
		SoftAssert sa = new SoftAssert();
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);

		for (int i = 0; i < psmp.getNavLinks().size(); i++) {
			List<WebElement> link = psmp.getNavLinks();
			link.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			String title = driver.getTitle().toLowerCase();
			sa.assertTrue(currentUrl.contains(title));
			psmp.getBackToMenuBtn().click();
		}
		for (int i = 0; i < psmp.getQuickLinks().size(); i++) {
			List<WebElement> link = psmp.getQuickLinks();
			link.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			String title = driver.getTitle().toLowerCase();
			sa.assertTrue(currentUrl.contains(title));
			psmp.getBackToMenuBtn().click();
		}
		for (int i = 0; i < psmp.getImageLinks().size(); i++) {
			List<WebElement> link = psmp.getImageLinks();
			link.get(i).click();
			String currentUrl = driver.getCurrentUrl().toLowerCase();
			String title = driver.getTitle().toLowerCase();
			sa.assertTrue(currentUrl.contains(title));
			psmp.getBackToMenuBtn().click();
		}
	}

	@Test
	public void SignInTest() {
		SoftAssert sa = new SoftAssert();
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		sa.assertTrue(psmp.isSignIn());
	}

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
