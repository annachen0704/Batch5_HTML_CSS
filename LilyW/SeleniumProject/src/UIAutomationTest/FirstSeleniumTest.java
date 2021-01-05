package UIAutomationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class FirstSeleniumTest {
	public static WebDriver driver =null;
	public static void main(String[] args) {
		
		
		try{
			System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
			driver = new ChromeDriver(); 
			//-> this is opening website as well, different from driver.navigate()
			driver.get("http://www.google.com/");//google
			WebElement searchBox = driver.findElement(By.name("q")); 
			/*
			 * Go to the website, we need to left click, and click "inspect".
			 * The HTML code will come out, click the square with the arrow, and then located the part, 
			 * for example in google the search bar.
			 * And the HTML will show the html related to the part we are pointing at.
			 * And then we need to find the unique name of the element we need.
			 * * Make sure the name is unique to the website -> command f (search bar will come out)
			 * we can use "//elementName[@name='theName']" -> only search in that specific element 
			 * or we can use "//*[@name='theName']"" -> this search at any level in the code
			 */
			searchBox.sendKeys("Christmas tree");
			searchBox.submit();// same as click enter
			//or instead enter we can click the "google search button"
			//WebElement submitButton = driver.findElement(By.xpath("//div[@class='FPdoLc tfB0Bf']/center/input[@name='btnK']")).Click();
			Thread.sleep(3000);
			
			// opening website
			/*Thread.sleep(1000);
			driver.navigate().to("http://www.amazon.com/");//amazon
			Thread.sleep(1000);
			driver.navigate().back();//google
			Thread.sleep(1000);
			driver.navigate().forward();//amazon
			Thread.sleep(1000);
			driver.navigate().refresh();//amazon
			Thread.sleep(1000);*/
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		} finally{
			if(driver !=null){
			driver.close();//we need to close and quit everytime
			driver.quit();
			}
		}
		
		
		
		
	}
}
