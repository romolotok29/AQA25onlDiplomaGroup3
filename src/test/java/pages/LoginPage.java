package pages;

import baseEntities.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By emailInput = By.id("name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("button_primary");

    public LoginPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return emailInput;
    }

    @Override
    protected String getPagePath() {
        return "";
    }

    public WebElement getEmail() {
        return wait.waitForVisibility(emailInput);
    }

    public LoginPage enterEmail(String email) {
        getEmail().sendKeys(email);
        return this;
    }

    public WebElement getPassword() {
        return wait.waitForVisibility(passwordInput);
    }

    public LoginPage enterPassword(String password) {
        getPassword().sendKeys(password);
        return this;
    }

    public WebElement getLoginButton() {
        return wait.waitForVisibility(loginButton);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public void fillUserInfo(User user) {
        this
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLoginButton();
    }

}