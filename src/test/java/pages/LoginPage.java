package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By emailInputLocator = By.id("name");
    private final By passwordInputLocator = By.id("password");
    private final By loginButtonLocator = By.id("button_primary");
    private final By loginErrorTextLocator = By.xpath("//div[@class='error-text']");

    public LoginPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return emailInputLocator;
    }

    @Override
    protected String getPagePath() {
        return "";
    }

    public WebElement getEmail() {
        return wait.waitForVisibility(emailInputLocator);
    }

    public LoginPage enterEmail(String email) {
        getEmail().sendKeys(email);
        return this;
    }

    public WebElement getPassword() {
        return wait.waitForVisibility(passwordInputLocator);
    }

    public LoginPage enterPassword(String password) {
        getPassword().sendKeys(password);
        return this;
    }

    public WebElement getLoginErrorText() {
        return wait.waitForVisibility(loginErrorTextLocator);
    }

    public String showLoginErrorMessage() {
        return getLoginErrorText().getText();
    }

    public WebElement getLoginButton() {
        return wait.waitForVisibility(loginButtonLocator);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

}