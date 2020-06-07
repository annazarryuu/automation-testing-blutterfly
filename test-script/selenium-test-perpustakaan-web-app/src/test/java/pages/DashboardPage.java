package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

	WebDriver driver;
	
	By modal_title = By.className("modal-title");
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTextInModalTitle() {
		return driver.findElement(modal_title).getText();
	}

}
