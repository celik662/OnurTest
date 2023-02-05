package Pac;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class LuckyClass {
	WebDriver driver;
	WebDriverWait wait;

	
	@BeforeClass	
	@Parameters({"browser"})
	public void Setup(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/onurcelik/Documents/Selenium Drivers/chromedriver");
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "/Users/onurcelik/Documents/Selenium Drivers/geckodriver");
			driver = new FirefoxDriver();
		}
		
		JavascriptExecutor js;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		
		
	}
	
	@Test
	public void Test1() throws InterruptedException {
		
		Thread.sleep(1000);
//		List<WebElement> navTools = driver.findElements(By.cssSelector("#nav-tools"));
//		System.out.println("navTools count: " + navTools.size());
//		Actions actions = new Actions(driver);
//		actions.moveToElement(navTools.get(1)).click();
		WebElement helloSignInButton = driver.findElement(By.cssSelector("#nav-link-accountList"));
		Actions actions = new Actions(driver);
		actions.moveToElement(helloSignInButton).build().perform();
		
//		driver.findElement(By.cssSelector("#nav-link-accountList")).click();
		WebElement signInButton = driver.findElement(By.cssSelector("div[id='nav-flyout-ya-signin'] span[class='nav-action-inner']"));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(signInButton));
		signInButton.click();;
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#ap_email")).sendKeys("onur1test1@gmail.com");
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#continue")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#ap_password")).sendKeys("123456789Aa.");
		Thread.sleep(200);
		driver.findElement(By.cssSelector("#signInSubmit")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#nav-hamburger-menu")).click();

		WebElement hMenu =driver.findElement(By.cssSelector("#hmenu-content"));
		wait.until(ExpectedConditions.visibilityOf(hMenu));
		
		List<WebElement>hMenuList = driver.findElements(By.cssSelector(".hmenu.hmenu-visible>li"));
		System.out.println("Menüdeki satır sayısı: " + hMenuList.size());
		
		for(int i=0;i<hMenuList.size();i++) {
			System.out.println("Satır Nu " + i + " " + hMenuList.get(i).getText().toString());
		}

		
		
		driver.close();
	}

}
