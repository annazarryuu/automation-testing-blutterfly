package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	
	By textbox_email = By.name("username");
	By textbox_password = By.name("password");
	By button_login = By.name("btnLogin");	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setTextInEmailField(String text) {
		driver.findElement(textbox_email).sendKeys(text);
	}

	public void setTextInPasswordField(String text) {
		driver.findElement(textbox_password).sendKeys(text);
	}
		
	public void clickLoginButton() {
		driver.findElement(button_login).sendKeys(Keys.RETURN);
	}
	
}
