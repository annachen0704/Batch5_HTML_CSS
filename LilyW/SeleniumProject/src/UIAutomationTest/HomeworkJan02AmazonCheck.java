package UIAutomationTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeworkJan02AmazonCheck {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
		WebDriver amazonDriver = new ChromeDriver();
		
		try {
			amazonDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			amazonDriver.navigate().to("https://www.amazon.com");
			Thread.sleep(2000);
			String theCurrentURL = amazonDriver.getCurrentUrl();
			String footerCheck = amazonDriver.findElement(By.xpath("//div[@class='navFooterLine navFooterLinkLine navFooterPadItemLine navFooterCopyright']//li[@class='nav_last']")).getText();
			//check if we are on amazon.com
			if (theCurrentURL.contains("amazon") && footerCheck.toLowerCase().contains("amazon")){
				System.out.println("The current website we are on is: "+ theCurrentURL );
				System.out.println("The message in the page footer is: " + footerCheck );
				System.out.println("--->> You are on the correct website");
			} else {
				String errorMessage ="You are currently on: " + theCurrentURL + "PLEASE CHECK AGAIN!";
				throw new Exception (errorMessage)
;			}
			
			//searching in the search bar
			WebElement searchBar = amazonDriver.findElement(By.id("twotabsearchtextbox"));
			String searchItem = "shampoo";
			searchBar.sendKeys(searchItem); 
			searchBar.submit();
			
			//check if we are on amazon.com that with candle result
			theCurrentURL = amazonDriver.getCurrentUrl();
			if (theCurrentURL.contains(searchItem) && footerCheck.toLowerCase().contains("amazon")){
				System.out.println("The current website we are on is: "+ theCurrentURL );
				System.out.println("The message in the page footer is: " + footerCheck );
				System.out.println("--->> You are on the correct website");
			} else {
				String errorMessage ="You are currently on: " + theCurrentURL + "PLEASE CHECK AGAIN!";
				throw new Exception (errorMessage)
;			}
		
			//find how many items on current page
			System.out.println("You are searching for [" +searchItem+"].");
			
			String parentLocator = "//*[@class='a-section a-spacing-medium']";
			//String titleLocator = "//*[@class='a-section a-spacing-medium']//h2";
			String titleLocator ="//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-4']";
			String priceLocator = "//*[@class='a-section a-spacing-medium']//span[@class='a-price']";
			
			
			List <WebElement> itemsOfThePage = amazonDriver.findElements(By.xpath(titleLocator));
			List <WebElement> priceOfTheItem = amazonDriver.findElements(By.xpath(priceLocator));
			Thread.sleep(3000);
			
			System.out.println("There are [" + itemsOfThePage.size() +"] results of the item you are seaching.");
			System.out.println("The detail list ---------->>>>>>>>");
			
			Iterator<WebElement> iter = itemsOfThePage.iterator();
			int i =0;
			while (iter.hasNext()){
				i++;
			System.out.println("No." +i);
			
			WebElement item = iter.next();
			String price = item.findElement(By.xpath(priceLocator)).getText();
			System.out.println("The price is: "+ price);
			String title = item.getText();
			System.out.println(title);
			}
			
			/*for (int index=0; index <itemsOfThePage.size();index++){
				
				System.out.println("No." +(index+1));
				String price = priceOfTheItem.get(index).getText();
				System.out.println(price+'\b');
				String theTitle = itemsOfThePage.get(index).getText();
				System.out.println(theTitle);
				
			
				
				
			}*/
			
			
			
			
			
			
			
			
			
			
			
		} catch(Exception e){
			System.out.println("Something went wrong!" + e.getMessage());
			e.printStackTrace();
		} finally {
			amazonDriver.close();
			amazonDriver.quit();
		}
		
		
		
	} // end of the main
}// end of the class
