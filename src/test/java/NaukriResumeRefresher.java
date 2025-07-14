import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NaukriResumeRefresher {
    public static void main(String[] args) throws InterruptedException {
        // Set path to ChromeDriver
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
//        WebDriver driver = new ChromeDriver();
        WebDriver driver;

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Add this line
        try {
            // Open Naukri login page
            driver.get("https://www.naukri.com/");
            driver.manage().window().maximize();
            Thread.sleep(3000);

            // Click on Login
            WebElement loginButton = driver.findElement(By.id("login_Layer"));
            loginButton.click();
            Thread.sleep(2000);

            // Enter credentials
            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"));
            WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder=\"Enter your password\"]"));
            emailField.sendKeys("virensingh2022@gmail.com");
            passwordField.sendKeys("Naukri@1234");

            // Submit login
            WebElement submitButton = driver.findElement(By.xpath("//button[text()='Login']"));
            submitButton.click();
            Thread.sleep(5000);

            driver.findElement(By.xpath("//div[@class=\"nI-gNb-drawer__icon\"]")).click();

//            // Wait for a more stable element on the next page to ensure the login is complete
            wait.until(ExpectedConditions.urlContains("naukri.com/mnjuser"));
            Thread.sleep(2000); // Small pause for the page to settle

//            driver.findElement(By.xpath("//a[text()=\"View & Update Profile\"]")).click();
            WebElement viewProfileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View & Update Profile']")));
            viewProfileLink.click();

            // Wait for the profile page to load
            wait.until(ExpectedConditions.urlContains("naukri.com/mnjuser/profile"));
            Thread.sleep(3000);

            // Click on "Update Resume" or "Upload Resume" button
            WebElement updateButton = driver.findElement(By.xpath("//input[@value=\"Update resume\"]"));
            updateButton.click();
            Thread.sleep(3000);

            System.out.println("Resume refreshed successfully!");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
