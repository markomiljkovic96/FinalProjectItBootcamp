package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getItemIds() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("item_id")));
	}

	public List<WebElement> getQuantities() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("quantity_input")));
	}

	public void setQuantity(int itemIndex, String quantity) {

		this.getQuantities().get(itemIndex).clear();
		this.getQuantities().get(itemIndex).sendKeys(quantity);
	}

	public WebElement getUpdateCartBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("update_cart")));
	}

	public WebElement getCheckoutBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("checkout")));
	}

	public WebElement getSubTotal() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("subtotal")));
	}

	public List<WebElement> getListPrices() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("list_price")));
	}

	public List<WebElement> getTotalPrice() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("total_price")));
	}

	public boolean cartIsEmpty() {
		boolean giveMessage = false;
		if (driver.findElement(By.xpath(locators.getProperty("cart_empty_message"))).isDisplayed()) {
			giveMessage = true;
		}
		return giveMessage;
	}
	
	// TODO Zbir cena svih proizvoda ukupno
	public double totalPriceList() {
		double sum = 0;
		for (int i = 0; i < getTotalPrice().size(); i++) {
			String price = this.getTotalPrice().get(i).getText().substring(1);
			double totalPrice = Double.parseDouble(price);

			sum += totalPrice;
		}
		return sum;
	}

	// TODO ukupna cena na dnu korpe
	private double subTotal() {
		String subTotal = this.getSubTotal().getText().substring(12);
		double total = Double.parseDouble(subTotal);
		return total;
	}

	public boolean isAddedToCart(String id) {
		boolean isAdded = true;
		try {
			this.driver.findElement(By.name(id));
		} catch (Exception e) {
			isAdded = false;
		}
		return isAdded;
	}

	// TODO poredjenje zbira cena sa zbirom na stranici
	public boolean isEqually() {
		boolean isSame = false;
		isSame = Math.abs(this.totalPriceList() - this.subTotal()) < 0.0001;
		if (this.totalPriceList() == this.subTotal()) {
			isSame = true;
		}
		return isSame;

	}
}