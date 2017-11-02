package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverFactory;

public class InboxPage extends Page {

    @FindBy(xpath = "//i[@id='PH_user-email']")
    private WebElement userMailStringAfterLogin;

    @FindBy(id = "PH_user-email")
    private WebElement loggedInUserMailLink;

    private WebDriverWait webDriverWait = new WebDriverWait(driver, DriverFactory.WEBDRIVER_WAIT_TIME_OUT);

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserMailAfterLoginDisplayed() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loggedInUserMailLink));
        return userMailStringAfterLogin.isDisplayed();
    }
}