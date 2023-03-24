package seleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		WebDriver d = new ChromeDriver(op);
		d.get("https://www.saucedemo.com/");
		
		d.findElement(By.id("user-name")).sendKeys("locked_out_user");
		d.findElement(By.id("password")).sendKeys("secret_sauce");
		d.findElement(By.id("login-button")).click();
		Thread.sleep(1000);
		System.out.println(d.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText());
		String logInError= "Epic sadface: Sorry, this user has been locked out.";
		Assert.assertEquals(d.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText(), logInError);
		d.quit();
		
	}

}
