package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "login_Layer")
    private WebElement loginBtn;

    @FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        submitBtn.click();
    }
}
