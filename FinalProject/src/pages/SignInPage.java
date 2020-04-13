package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class SignInPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public WebElement getSignInBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signin_btn")));
	}

	public WebElement getUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("username_input")));
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("password_input")));
	}

	public void setUsername(String username) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
	}

	public void setPassword(String pass) {
		this.getPassword().clear();
		this.getPassword().sendKeys(pass);
	}

	public WebElement getLoginBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("login_btn")));

	}

	public WebElement getRegisterBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("register_btn")));
	}

	public WebElement getSignoutBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signout_btn")));
	}

	public WebElement getMyAccBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("myacc_btn")));
	}

	public void login(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		this.getLoginBtn().click();
	}

	public boolean isSignIn() {
		boolean isLogged = true;
		try {
			this.driver.findElement(By.xpath(this.locators.getProperty("welcome_message")));

		} catch (Exception e) {
			isLogged = false;
		}
		return isLogged;

	}

	public boolean isExpected(int i) {
		boolean ok = false;

		if (ExcelUtils.getDataAt(i, 17) == "TRUE") {
			ok = true;

		}
		return ok;
	}
}

