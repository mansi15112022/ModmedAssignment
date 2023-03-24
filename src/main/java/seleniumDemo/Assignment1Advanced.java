package seleniumDemo;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class Assignment1Advanced {

	private static final DecimalFormat decfor = new DecimalFormat("0.00");

	static WebDriver d;

	public static void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollBy(0,350)", "");
	}

	public static void main(String[] args) throws InterruptedException {

		String name;
		String toBeRemoved = "Sauce Labs";
		int j = 0;
		HashMap<String, String> expPrice = new HashMap<>();

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		d = new ChromeDriver(op);
		d.get("https://www.saucedemo.com/");
		d.manage().window().maximize();

		String[] itemsNeeded = { "Bike Light", "Bolt T-Shirt", "Fleece Jacket", "Onesie" }; // array of required items
		List<String> itemsNeededList = Arrays.asList(itemsNeeded);
		
		//login details
		d.findElement(By.id("user-name")).sendKeys("standard_user");
		d.findElement(By.id("password")).sendKeys("secret_sauce");
		d.findElement(By.id("login-button")).click();

		List<WebElement> itemPrice = d.findElements(By.xpath("//div[@class='pricebar']/button/../div"));
		List<WebElement> products = d.findElements(By.xpath("//div[@class='inventory_item_name']"));

		for (WebElement webElement : products) {
			System.out.println(webElement.getText());
		}
		
		//loop to find required items from all other items and price extraction
		for (int i = 0; i < products.size(); i++) {

			name = products.get(i).getText();

			if (itemsNeededList.contains(name.replaceAll(toBeRemoved, " ").trim())) {
				j++;
				d.findElements(By.xpath("//div[@class='pricebar']/button")).get(i).click();
//				if(i!=1)
				expPrice.put(name, itemPrice.get(i).getText().replace("$", ""));
				//used this counter with break to unnecessary iterations the moment we get our required items
				if (j == itemsNeeded.length) {  
					break;
				}
			}

		}

		System.out.println("Item name and price before removing second item " + expPrice);

		System.out.println("Price of the second item being removed "
				+ expPrice.remove(toBeRemoved + " " + itemsNeededList.get(1)));

		d.findElement(By.xpath("//div[@id='shopping_cart_container']/a")).click();

		List<WebElement> removeButtons = d.findElements(By.xpath("//button[contains(text(), 'Remove')]"));
		removeButtons.get(1).click(); // removing second item

		d.findElement(By.xpath("//div[@class='cart_footer']/button[2]")).click(); // continue

		// filling the details
		d.findElement(By.id("first-name")).sendKeys("Mansi");
		d.findElement(By.id("last-name")).sendKeys("Sahu");
		d.findElement(By.id("postal-code")).sendKeys("226017");
		d.findElement(By.id("continue")).click();

		// VERIFY PRICE
		// item tot = //div[@class='summary_subtotal_label']
		// tax = //div[@class='summary_tax_label']
		// summary info = //div[@class='summary_info_label summary_total_label']
		scrollToBottom();
		WebElement subTotal = d.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
		WebElement tax = d.findElement(By.xpath("//div[@class='summary_tax_label']"));
		WebElement grandTotal = d.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"));

		float sTotal = 0;
		for (Map.Entry<String, String> a : expPrice.entrySet()) {
			sTotal = sTotal + Float.parseFloat(a.getValue());
		}

		float expTax = (float) (sTotal * 0.08);
		float gTotal = sTotal + expTax;

		String actTax = tax.getText().split(" ")[1];
		String actTot = subTotal.getText().split(" ")[2];
		String actGTot = grandTotal.getText().split(" ")[1];

		Assert.assertEquals(actTax.substring(1, actTax.length()), decfor.format(expTax).toString()); 
		Assert.assertEquals(actTot.substring(1, actTot.length()), Float.toString(sTotal));
		Assert.assertEquals(actGTot.substring(1, actGTot.length()), decfor.format(gTotal).toString());

		System.out.println(d.findElement(By.xpath("//div[@class='summary_info']/div")).getText());
		System.out.println(d.findElement(By.xpath("//div[@class='summary_info']/div[2]")).getText());
		System.out.println(d.findElement(By.xpath("//div[@class='summary_info']/div[8]")).getText());
		d.findElement(By.id("finish")).click();

		System.out.println(d.findElement(By.xpath("//div[@id='checkout_complete_container']/h2")).getText());
		String confirmationMessage = "Thank you for your order!";
		Assert.assertEquals(d.findElement(By.xpath("//div[@id='checkout_complete_container']/h2")).getText(),
				confirmationMessage);
		System.out.println(d.findElement(By.xpath("//div[@class= 'complete-text']")).getText());
		System.out.println(d.findElement(By.xpath("//div[@id='checkout_complete_container']/img")).isDisplayed());

		Assert.assertTrue(d.findElement(By.xpath("//div[@id='checkout_complete_container']/img")).isDisplayed());
		d.quit();
	}

}
