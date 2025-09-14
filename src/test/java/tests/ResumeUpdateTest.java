package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class ResumeUpdateTest extends BaseTest {

    @Test
    public void testResumeUpdate() {
        pageObjectManager.getLoginPage().clickLoginButton();
        pageObjectManager.getLoginPage().enterEmail(ConfigReader.getProperty("username"));
        pageObjectManager.getLoginPage().enterPassword(ConfigReader.getProperty("password"));
        pageObjectManager.getLoginPage().submitLogin();

        pageObjectManager.getHomePage().navigateToProfile();

        pageObjectManager.getProfilePage().clickUpdateResume();
        pageObjectManager.getProfilePage().uploadResume(ConfigReader.getProperty("resumePath"));

        System.out.println("✅ Resume updated successfully!");
    }
}
