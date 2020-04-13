package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class RegistrationPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("userid_input")));
	}

	public void setUserId(String userid) {
		this.getUserId().clear();
		this.getUserId().sendKeys(userid);
	}

	public WebElement getNewPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("new_pass")));
	}

	public void setNewPass(String pass) {
		this.getNewPass().clear();
		this.getNewPass().sendKeys(pass);
	}

	public WebElement getRepeatPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeat_pass")));
	}

	public void setRepeatPass(String pass) {
		this.getRepeatPass().clear();
		this.getRepeatPass().sendKeys(pass);
	}

	public WebElement getFirstname() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("firstname_input")));
	}

	public void setFirstname(String firstname) {
		this.getFirstname().clear();
		this.getFirstname().sendKeys(firstname);
	}

	public WebElement getLastname() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("lastname_input")));
	}

	public void setLastname(String lastname) {
		this.getLastname().clear();
		this.getLastname().sendKeys(lastname);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("email_input")));
	}

	public void setEmail(String mail) {
		this.getEmail().clear();
		this.getEmail().sendKeys(mail);
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone_input")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("adress1_input")));
	}

	public void setAddress1(String address) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address);
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("adress2_input")));
	}

	public void setAddress2(String address) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address);
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city_input")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state_input")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip_input")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country_input")));
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public WebElement getLanguagePrefBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("lng_pref")));
	}

	// TODO Izbor jezika
	public void clickToLanguage(int i) {
		List<WebElement> links = driver.findElements(By.xpath(this.locators.getProperty("lnguage_list")));
		this.getLanguagePrefBox().click();
		for (int j = 0; j < links.size(); j++) {
			if (links.get(j).getText().contains(ExcelUtils.getDataAt(i, 12))) {
				links.get(j).click();
				break;
			}
		}
	}

	public WebElement getFavCatgBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("fav_catg")));
	}

	// TODO Izbor omiljene zivotinje
	public void clickToFavoriteAnimal(int i) {
		List<WebElement> links = driver.findElements(By.xpath(this.locators.getProperty("fav_animal")));

		this.getFavCatgBox().click();

		for (int j = 0; j < links.size(); j++) {
			if (links.get(j).getText().contains(ExcelUtils.getDataAt(i, 13))) {
				links.get(j).click();
				break;
			}
		}
	}

	public WebElement getEnbMyListBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enb_mylist")));
	}

	public void clickToListBox(int i) {

		Boolean is_selected = getEnbMyListBox().isSelected();
		if (ExcelUtils.getDataAt(i, 14) == "TRUE" && is_selected == false) {
			this.getEnbMyListBox().click();

		} else if (ExcelUtils.getDataAt(i, 14) == "FALSE" && is_selected == true) {
			this.getEnbMyListBox().click();
		}

	}

	public WebElement getEnbableMyBanerBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enb_mybaner")));
	}

	public void clickToEnableMyBanerBox(int i) {

		Boolean is_selected = getEnbableMyBanerBox().isSelected();
		if (ExcelUtils.getDataAt(i, 15) == "TRUE" && is_selected == false) {
			this.getEnbableMyBanerBox().click();
		} else if (ExcelUtils.getDataAt(i, 15) == "FALSE" && is_selected == true) {
			this.getEnbMyListBox().click();
		}

	}

	public WebElement getSaveData() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("saveacc_btn")));
	}

	public WebElement getUrlAction() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("url_afterreg")));
	}

	public void fillForm(String userid, String password, String pass, String firstname, String lastname, String mail,
			String phone, String address1, String address2, String city, String state, String zip, String country) {
		this.setUserId(userid);
		this.setNewPass(password);
		this.setRepeatPass(pass);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(mail);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);

	}

	public void fillFormFavorite(int i) {
		this.clickToLanguage(i);
		this.clickToFavoriteAnimal(i);
		this.clickToListBox(i);
		this.clickToEnableMyBanerBox(i);
		this.getSaveData().click();
	}

	public boolean isRegistred() {
		boolean isLogged = true;
		try {
			this.driver.findElement(By.xpath(this.locators.getProperty("petstore_logo")));
		} catch (Exception e) {
			isLogged = false;
		}
		return isLogged;

	}

	public boolean isExpected(int i) {
		boolean ok = false;

		if (ExcelUtils.getDataAt(i, 16) == "TRUE") {
			ok = true;

		}
		return ok;
	}

}
