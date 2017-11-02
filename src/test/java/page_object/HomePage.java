package page_object;

import business_object.user.User;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(xpath = "//input[@id='mailbox:login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='mailbox:password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@id='PH_authLink']")
    private WebElement enterLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check Browser type (sometimes it's necessary to perform actions with only one browser type).
     * If browser == IE - handle alert window, then fill in the form
     * If browser != IE - fill in the form without handling alert window
     */
    public void loginToMailBox(User user) {
        if (driver.getClass().getName().equalsIgnoreCase("org.openqa.selenium.ie.InternetExplorerDriver")) {
            alertHandle();
            fillTheForm(user);
        } else {
            fillTheForm(user);
        }
    }

    public InboxPage fillTheForm(User user) {
        loginInput.clear();
        loginInput.sendKeys(user.getLogin());
        passwordInput.sendKeys(user.getPassword());
        signInButton.click();
        return new InboxPage(driver);
    }

    /**
     * Check if alert window is presented
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            System.out.println("ALERT window is presented");
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /**
     * If alert window is presented - accept it
     * (repeat for all alert windows)     */
    public void alertHandle() {
        while (isAlertPresent()) {
            driver.switchTo().alert().accept();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}