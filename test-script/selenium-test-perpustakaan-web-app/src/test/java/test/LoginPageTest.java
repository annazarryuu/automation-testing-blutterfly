package test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;

public class LoginPageTest {

	private static WebDriver driver;
	
	@BeforeTest
	private static void initWebDriver() {
		String projectPath = System.getProperty("user.dir");		
		System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	private static void loginAsAdminTest() {
		LoginPage loginPage = new LoginPage(driver);
		DashboardPage dashboardPage = new DashboardPage(driver);
		String url = "http://localhost:8080/PerpustakaanWebApp/login";
		driver.get(url);
		String name = "januar";
		String username = name + "@email.com";
		String password = "123456";
		loginPage.setTextInEmailField(username);
		loginPage.setTextInPasswordField(password);
		loginPage.clickLoginButton();		
		String role = "ADMINISTRATOR";
		String expectedText = "WELCOME " + name + " as " + role + " POLBAN LIBRARY";
		assertEquals(expectedText, dashboardPage.getTextInModalTitle());
	}

	@AfterTest
	private static void closeWebDriver() {
		driver.close();
		driver.quit();
	}
	
}
