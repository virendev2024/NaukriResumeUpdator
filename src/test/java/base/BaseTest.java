package base;

import factory.DriverFactory;
import managers.PageObjectManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    protected PageObjectManager pageObjectManager;

    @BeforeClass
    public void setUp() {
        ConfigReader.loadProperties();

        String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
        driver = DriverFactory.getDriver(browser);

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));

        pageObjectManager = new PageObjectManager(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
        }
    }
}
