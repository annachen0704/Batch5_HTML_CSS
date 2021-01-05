package UIAutomationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;



public class BoraWebTest {
	public static WebDriver boraDriver =null;
	
	public static void main(String[] args) {
		try{
			System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
			boraDriver = new ChromeDriver(); 
			boraDriver.navigate().to("http://boratech.herokuapp.com");
			WebElement boraLogin = boraDriver.findElement(By.linkText("Login"));
			boraLogin.click();
			Thread.sleep(1000);
			WebElement boraEmail = boraDriver.findElement(By.name("email"));
		
			boraEmail.sendKeys("lilywang0427@gmail.com");
			WebElement boraPassword = boraDriver.findElement(By.name("password"));
			boraPassword.sendKeys("Xiaohulideai123l");
			WebElement boraLoginSubmit =boraDriver.findElement(By.xpath("//input[@type='submit']"));
			boraLoginSubmit.click();
			Thread.sleep(1000);
			WebElement boraCommunityPage = boraDriver.findElement(By.linkText("BoraCommunity"));
			boraCommunityPage.click();
			Thread.sleep(5000);
			
			List <WebElement> boraList = boraDriver.findElements(By.linkText("sumbit"));
	
			
			
			
			
			}catch(Exception e){
				e.printStackTrace();
				
			} finally{
				if(boraDriver !=null){
				boraDriver.close();//we need to close and quit everytime
				boraDriver.quit();
				}
			}
			
			
		
	}

}
