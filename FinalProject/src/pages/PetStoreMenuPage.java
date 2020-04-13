package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getNavLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("left_nav_links")));
	}
	
	public List<WebElement> getQuickLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("quick_links")));
	}

	public List<WebElement> getImageLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("pict_links")));
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signin_btn")));
	}

	public WebElement getBackToMenuBtn() {
		return driver.findElement(By.xpath(this.locators.getProperty("return_menu")));
}
	public WebElement getMessage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("message")));
	}

	public boolean isSignIn() {
		this.getSignIn().click();
		return this.getMessage().getText().contains("Please enter your username and password.");
	}
}
