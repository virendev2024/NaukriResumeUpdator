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
//        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void refreshResume() throws InterruptedException {
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        driver.findElement(By.id("login_Layer")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")).sendKeys("virensingh2022@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder=\"Enter your password\"]")).sendKeys("Naukri@1234");

        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@class=\"nI-gNb-drawer__icon\"]")).click();

        wait.until(ExpectedConditions.urlContains("naukri.com/mnjuser"));
        Thread.sleep(2000);

        WebElement viewProfileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View & Update Profile']")));
        viewProfileLink.click();

        wait.until(ExpectedConditions.urlContains("naukri.com/mnjuser/profile"));
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@value=\"Update resume\"]")).click();
        Thread.sleep(3000);

        System.out.println("Resume refreshed successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
