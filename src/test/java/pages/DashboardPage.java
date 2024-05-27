package pages;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.project.ProjectInfoPage;

import java.util.List;

public class DashboardPage extends BasePage {
    private final static String pagePath = "";
    private final By addProjectSideButton = By.id("sidebar-projects-add");
    private final By topMenuUserButton = By.cssSelector(".navigation-username");
    private final By topMenuUserLogoutButton = By.id("navigation-user-logout");
    private final By topMenuSearchButton = By.xpath("//input[@id='search_query']");
    private final By topSearchDialogWindow = By.id("top_search_dialog");
    private final By projectsOnDashboard = By.xpath("//a[contains (@href, 'projects/overview')]");
    private final By copyToClipboardButton = By.xpath("//div[@tooltip-text='Copy to Clipboard']");
    private final By copyToClipboardHiddenText = By.xpath("//p[contains(text(), 'Copy to Clipboard')]");

    public DashboardPage(WebDriver driver) {
        this(driver, false);
    }
    public DashboardPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return addProjectSideButton;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getAddProjectSideButton() {
        return wait.waitForVisibility(addProjectSideButton);
    }
    public WebElement getTopMenuUserButton() {
        return wait.waitForVisibility(topMenuUserButton);
    }
    public void clickTopMenuUserButton() {
        getTopMenuUserButton().click();
    }
    public WebElement getTopMenuSearchButton() {
        return wait.waitForVisibility(topMenuSearchButton);
    }
    public WebElement getTopSearchDialogWindow() {
        return wait.waitForVisibility(topSearchDialogWindow);
    }
    public void clickTopMenuSearchButton() {
        getTopMenuSearchButton().click();
    }

    public WebElement getTopMenuUserLogoutButton() {
        return wait.waitForVisibility(topMenuUserLogoutButton);
    }

    public void clickTopMenuUserLogoutButton() {
        getTopMenuUserLogoutButton().click();
    }

    public boolean isDialogWindowDisplayed() {
        if (getTopSearchDialogWindow().isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public List<WebElement> getProjectInGrid() {
        return wait.waitForAllVisibleElementsLocatedBy(projectsOnDashboard);
    }

    public WebElement copyToClipboardButton() {
        return wait.waitForVisibility(copyToClipboardButton);
    }

    public WebElement copyToClipboardHiddenText() {
        return wait.waitForVisibility(copyToClipboardHiddenText);
    }

    public String showCopyToClipboardHiddenText() {
        return copyToClipboardHiddenText().getText();
    }

    public ProjectInfoPage clickOnProjectInGrid(Project project) {
        for (WebElement element :
                getProjectInGrid()) {
            if (element.getText().trim().equals(project.getName())) {
                element.click();

                return new ProjectInfoPage(driver, false);
            }
        }
        return null;
    }

}