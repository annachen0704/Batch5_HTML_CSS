package UIAutomationTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework0102Amazon {

	public static void main(String[] args) throws InterruptedException {
		
		// get to the amazon page
		System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
		WebDriver amazonDriver = new ChromeDriver();
		amazonDriver.navigate().to("https://www.amazon.com");
		Thread.sleep(2000);
		
		//check if we are on amazon.com
		String currentURL = amazonDriver.getCurrentUrl();
		System.out.println("The current URL is: " + currentURL);
		WebElement checkFooter = amazonDriver.findElement(By.xpath("//div[@class='navFooterLine navFooterLinkLine navFooterPadItemLine navFooterCopyright']//li[@class='nav_last']"));
		String footerText = checkFooter.getText();
		if (currentURL.contains("amazon") && footerText.contains("amazon"))
		{
			System.out.println("We are current on " + currentURL + ". Please continue");
			
		} else {
			System.out.println("sOMETHING WRONG");
		}
		
		WebElement amazonSearchBar = amazonDriver.findElement(By.id("twotabsearchtextbox"));
		amazonSearchBar.sendKeys("shampoo");
		amazonSearchBar.submit();
		Thread.sleep(2000);
		
		//check the h2
		String xpathForEachBox = "//div[@class='s-expand-height s-include-content-margin s-border-bottom s-latency-cf-section']";
		String xpathForTitle = "//span[@class='a-size-base-plus a-color-base a-text-normal']";
		String xpathForPriceWhole = "//span[@class='a-price-whole']";
		String xpathForPriceFraction ="//span[@class='a-price-fraction']";
		
		List<WebElement> searchResultTitle = amazonDriver.findElements(By.xpath(xpathForEachBox));
		List<WebElement> titles = amazonDriver.findElements(By.xpath(xpathForEachBox+xpathForTitle));
		List<WebElement> pricesTheWhole = amazonDriver.findElements(By.xpath(xpathForEachBox+xpathForPriceWhole));
		List<WebElement> pricesTheFraction = amazonDriver.findElements(By.xpath(xpathForEachBox+xpathForPriceFraction));
		for (int index = 0; index < searchResultTitle.size();index++){
			
			
			String theTitle = titles.get(index).getText();
			String thePriceWhole = pricesTheWhole.get(index).getText();
			String thePriceFraction = pricesTheFraction.get(index).getText();
			System.out.println("No.[" + (index+1) +"]" + " $" +thePriceWhole +"."+thePriceFraction);
			System.out.println("The produce name is: " +theTitle);
		}
			
			
			
			
			
		//close and quit the chromedriver
		amazonDriver.close();
		amazonDriver.quit();
		
		
		
		
	}
	public static void checkCurrentPage (String expectedURL, String expectedText){
		
	}
}
