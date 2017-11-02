package test;

import business_object.user.User;
import business_object.user.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_object.HomePage;
import page_object.InboxPage;
import setup.Browser;
import setup.WebDriverTypes;

public class MailRuCriticalPathTest {

    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private HomePage homePage;
    private InboxPage inboxPage;
    private User sender;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {

        /**
         * Change browser type here - IE, CHROME or FIREFOX
         */
        driver = Browser.getWrappedDriver(WebDriverTypes.IE);

        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        sender = UserFactory.createDefaultUser();
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox() {
        new HomePage(driver).loginToMailBox(sender);
        boolean loginIsComplete = inboxPage.isUserMailAfterLoginDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed successfully");
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}