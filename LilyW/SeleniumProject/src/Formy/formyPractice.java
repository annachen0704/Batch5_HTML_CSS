package Formy;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class formyPractice {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://formy-project.herokuapp.com/keypress");
		
		
		//Project Keyboard and mouse input
		WebElement enterName = driver.findElement(By.id("name"));
		enterName.sendKeys("Xingyue Wang");
		driver.findElement(By.id("button")).click();
		
		
		//using auto-complete 
		driver.get("https://formy-project.herokuapp.com/autocomplete");
		WebElement autoComplete = driver.findElement(By.id("autocomplete"));
		autoComplete.sendKeys("1064 utterback store rd, great falls, va");
		Thread.sleep(3000);
		WebElement autoCompleteResult = driver.findElement(By.className("pac-item"));
		autoCompleteResult.click();
		Thread.sleep(3000);
		
		// scroll to the element ---> long document scroll to the place we need to test
		driver.get("https://formy-project.herokuapp.com/scroll");
		WebElement signName = driver.findElement(By.id("name"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signName);
		signName.sendKeys("Xingyue Wang");
		WebElement date = driver.findElement(By.id("date"));
		date.sendKeys("01/04/2021");
		
		
		/* Some applications have multiple windows, frams, and alerts that open on a page.
		 * WebDriver assigns each window a unique ID.
		 * Three ways to switch windows
		 * 1. driver.switchTo().window("windowName");
		 * 2. driver.switchTo().frame("frameName");
		 * 3. driver.switchTo().alert();
		 */
		driver.get("https://formy-project.herokuapp.com/switch-window");
		WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
		newTabButton.click(); //----> by clicking this button we are on different tab we are just opened
		String originalHandle = driver.getWindowHandle(); // even though another tab is opened at this point, the driver is still focusing on the orignial window open.
		for (String handel1:driver.getWindowHandles()){
			driver.switchTo().window(handel1);
		} // ????????? why use this for loop, seems like with or without this loop is the same?????
		driver.switchTo().window(originalHandle);
		Thread.sleep(3000);
		
		WebElement newAlertButton = driver.findElement(By.id("alert-button"));
		newAlertButton.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		
	
		
		driver.close();
		driver.quit();
		
	}

}
