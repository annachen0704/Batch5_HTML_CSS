package UIAutomationTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeworkDec26 {

	public static WebDriver boraDriver = null;

	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
			boraDriver = new ChromeDriver();
			boraDriver.navigate().to("http://boratech.herokuapp.com");
			WebElement loginPageButton = boraDriver.findElement(By.linkText("Login"));
			loginPageButton.click();
			WebElement inputEmail = boraDriver.findElement(By.name("email"));
			inputEmail.sendKeys("lilywang0427@gmail.com");
			WebElement inputPassword = boraDriver.findElement(By.name("password"));
			inputPassword.sendKeys("Xiaohulideai123l");
			WebElement signInLoginButton = boraDriver.findElement(By.xpath("// input[@ type ='submit']"));
			signInLoginButton.click();
			Thread.sleep(2000);
			WebElement postButton = boraDriver.findElement(By.xpath("//a[starts-with(@href, '/posts')]"));
			postButton.click();
			Thread.sleep(2000);
			WebElement commentBox = boraDriver.findElement(By.name("text"));
			commentBox.sendKeys("Delete this please");
			Thread.sleep(2000);
			WebElement submitButtonForComment = boraDriver.findElement(By.xpath("//input[@ type='submit']"));
			submitButtonForComment.click();
			commentBox.sendKeys("Hi,This is Lily. I am doing my homework!");
			submitButtonForComment.click();
			Thread.sleep(2000);
			boraDriver.navigate().refresh();
			Thread.sleep(2000);

			List<WebElement> checkPost = boraDriver.findElements(By.xpath("//p[@ class='my-1']"));

			Thread.sleep(3000);

			String targetText = "Delete this please";

			List<WebElement> checkButton = boraDriver.findElements(
					By.xpath("//div[@ class='post bg-white p-1 my-1']//button[@ class='btn btn-danger']"));
			for (int i = 0; i < checkButton.size(); i++) {
				String checkText = checkPost.get(i).getText();
				System.out.println(checkText);
				if (checkText.equalsIgnoreCase(targetText)) {
					
					 System.out.println("Its found"); System.out.println(i+1);
					 
					int indexNumber = i + 1;

					boraDriver.findElement(By.xpath("//div[@ class='post bg-white p-1 my-1'][" + indexNumber
							+ "]//button[@ class='btn btn-danger']")).click();
					;

					Thread.sleep(2000);
					break;

				} else {
					System.out.println("Nothing is found");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (boraDriver != null) {
				boraDriver.close();
				boraDriver.quit();
			}

		}
	}
}
