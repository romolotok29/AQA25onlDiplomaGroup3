package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IntegrationPage extends BasePage {
     private static final String pagePath = "/index.php?/admin/integration";
     private final By integrationPageIdentifier = By.cssSelector("#settings-integration-tabs-integrations");
     private final By configureIntegrationButton = By.xpath
             ("//a[@onclick='this.blur(); App.Admin.Integration.configureJira(); return false;']");
     private final By jiraIntegrationAddressInputLocator = By.cssSelector("#jiraIntegrationAddress");
     private final By jiraVersionButtonLocator = By.cssSelector("#jiraIntegrationVersion");
     private final By jiraEmailAddressInputLocator = By.cssSelector("#jiraIntegrationUser");
     private final By jiraApiTokenInputLocator = By.cssSelector("#jiraIntegrationUser");
     private final By enableJiraIntegrationButtonLocator = By.cssSelector("#jiraIntegrationSubmit");

    public IntegrationPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return integrationPageIdentifier;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getConfigureIntegrationButton() {
        return wait.waitForVisibility(configureIntegrationButton);
    }

    public WebElement getJiraAddressInput() {
        return wait.waitForVisibility(jiraIntegrationAddressInputLocator);
    }

    public WebElement getJiraVersionButton() {
        return wait.waitForVisibility(jiraVersionButtonLocator);
    }

    public WebElement getJiraEmailAddressInput() {
        return wait.waitForVisibility(jiraEmailAddressInputLocator);
    }

    public WebElement getJiraApiTokenInput() {
        return wait.waitForVisibility(jiraApiTokenInputLocator);
    }

    public WebElement getEnableJiraIntegrationButton() {
        return wait.waitForVisibility(enableJiraIntegrationButtonLocator);
    }

    public IntegrationPage enterJiraAddress(String address) {
        getJiraAddressInput().sendKeys(address);
        return this;
    }

    public IntegrationPage enterJiraEmailAddress(String email) {
        getJiraEmailAddressInput().sendKeys(email);
        return this;
    }

    public IntegrationPage enterJiraApiToken(String token) {
        getJiraApiTokenInput().sendKeys(token);
        return this;
    }

    public IntegrationPage clickConfigureIntegrationButton() {
        getConfigureIntegrationButton().click();
        return this;
    }

    public IntegrationPage clickJiraVersionButton() {
        getJiraVersionButton().click();
        return this;
    }

    public void clickEnableJiraIntegrationButton() {
        getEnableJiraIntegrationButton().click();
    }
}
