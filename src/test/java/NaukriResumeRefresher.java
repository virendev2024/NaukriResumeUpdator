import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class NaukriResumeRefresher {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void refreshResume() {
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();

        // Wait for login button and click
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login_Layer")));
        loginBtn.click();

        // Wait for email field
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Enter your active Email ID / Username']")));
        emailField.sendKeys("virensingh2022@gmail.com");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Enter your password']")));
        passwordField.sendKeys("Naukri@1234");

        // Click login
        WebElement loginSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Login']")));
        loginSubmitBtn.click();

        // Wait for profile icon and click
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='nI-gNb-drawer__icon']")));
        profileIcon.click();

        // Wait for profile link and click
        WebElement viewProfileLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='View & Update Profile']")));
        viewProfileLink.click();

        // Wait for "Update resume" button and click
        WebElement updateResumeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Update resume']")));
        updateResumeBtn.click();

        // Wait for file input and upload file
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("attachCV")));
        fileInput.sendKeys("C:\\Users\\91991\\IdeaProjects\\NaukriResumeUpdator\\src\\test\\java\\FileUpload\\Virender_QA3+.pdf");

        System.out.println("Resume refreshed successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
