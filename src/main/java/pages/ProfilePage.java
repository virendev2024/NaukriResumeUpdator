package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@value='Update resume']")
    private WebElement updateResumeBtn;

    @FindBy(id = "attachCV")
    private WebElement fileUploadInput;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickUpdateResume() {
        updateResumeBtn.click();
    }

    public void uploadResume(String filePath) {
        fileUploadInput.sendKeys(filePath);
    }
}
