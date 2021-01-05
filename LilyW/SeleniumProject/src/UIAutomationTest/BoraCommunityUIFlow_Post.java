package UIAutomationTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraCommunityUIFlow_Post {

	public static WebDriver driver;
	public static String postMessage = "Hey guy, happy new year, let's make 2021 different!";

	public static void main(String[] args) {
		
		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMddHHmmssS");
		Date date = new Date();
		postMessage = timeStamp.format(date) + " - " + postMessage;

		try {
			System.setProperty("webdriver.chrome.driver", "src/resource/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.navigate().to("https://boratech.herokuapp.com/");

			// log in
			driver.findElement(By.linkText("Login")).click();
			verifyPageLoad("Sign In", "login");
			driver.findElement(By.name("email")).sendKeys("murad@test.com");
			driver.findElement(By.name("password")).sendKeys("murad001");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			waitFor(1);
			verifyPageLoad("Dashboard", "dashboard");

			// create a post
			driver.findElement(By.linkText("Posts")).click();
			waitFor(1);
			verifyPageLoad("Posts", "posts");

			driver.findElement(By.tagName("textarea")).sendKeys(postMessage);
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			driver.navigate().refresh();
			waitFor(1);

			// validate post exists
			String parentLocator = "(//div[contains(@class, 'post bg-white')])";
			List<WebElement> postContainers = driver.findElements(By.xpath(parentLocator));
			System.out.println("==> Number of posts available: " + postContainers.size());

			boolean found = false;
			String targetPostContainerLocator = null;
			for (int i = 1; i <= postContainers.size(); i++) {
				String currentContainerLocator = parentLocator + "[" + i + "]";
				String postText = driver.findElement(By.xpath(currentContainerLocator + "/div/p[@class='my-1']"))
						.getText();
				if (postText.equalsIgnoreCase(postMessage)) {
					found = true;
					targetPostContainerLocator = currentContainerLocator;
					break;
				}
			}

			if (found) {
				System.out.println("==> Found the post!");
			} else {
				throw new Exception("Post not found.");
			}

			// delete the post
			try {
				WebElement deleteButton = driver
						.findElement(By.xpath(targetPostContainerLocator + "/div/button[@class='btn btn-danger']"));
				System.out.println("==> Delete button is found.");
				deleteButton.click();
				System.out.println("==> Delete button is clicked.");
			} catch (NoSuchElementException e) {
				throw new Exception("Delete button for the target post does not exist.");
			}

			// validate deletion
			// step 1 - alert
			String expectedAlertText = "Post Removed";
			WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
			String actualAlertText = alert.getText();

			if (expectedAlertText.equalsIgnoreCase(actualAlertText)) {
				System.out.println("==> Alert recevied as expected.");
			} else {
				throw new Exception("[Post removed] alert not found.");
			}

			waitFor(1);

			// step 2 - check posts
			postContainers = driver.findElements(By.xpath(parentLocator));
			System.out.println("==> Number of posts available: " + postContainers.size());

			found = false;
			for (int i = 1; i <= postContainers.size(); i++) {
				String postText = driver.findElement(By.xpath(parentLocator + "[" + i + "]" + "/div/p[@class='my-1']"))
						.getText();
				if (postText.equalsIgnoreCase(postMessage)) {
					found = true;
					break;
				}
			}

			if (found) {
				throw new Exception("Post found, deletion was not successful.");
			} else {
				System.out.println("==> Deletion successful.");
			}

			System.out.println("==> Test Passed!");
		} catch (Exception e) {
			System.out.println("==> Test Failed:");
			System.out.println(e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

	public static void verifyPageLoad(String expectedPageTitle, String expectedPartialUrl) throws Exception {
		WebElement pageTitle = driver.findElement(By.xpath("//h1[@class='large text-primary']"));
		String actualPageTitle = pageTitle.getText();

		String currentPageUrl = driver.getCurrentUrl();
		System.out.println("==> Current URL: " + currentPageUrl);

		if (expectedPageTitle.equalsIgnoreCase(actualPageTitle) && currentPageUrl.contains(expectedPartialUrl)) {
			System.out.println("==> We're good, " + expectedPageTitle + " page is loaded, continuing...");
		} else {
			String exceptionMessage = "\n\tExpected Page Title: " + expectedPageTitle + "\n\tActual Page Title: "
					+ actualPageTitle;
			throw new Exception(exceptionMessage);
		}

		System.out.println();
	}

	public static void waitFor(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

}




