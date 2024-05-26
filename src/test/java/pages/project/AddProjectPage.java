package pages.project;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddProjectPage extends BasePage {
    private final static String pagePath = "/index.php?/admin/projects/add/1";
    private final By projectNameInput = By.id("name");
    private final By addProjectButton = By.id("accept");
    private final By announcementTextInput = By.id("announcement_display");
    private final By showAnnouncementCheckbox = By.id("show_announcement");
    private final By modeRadioButtonsList = By.name("suite_mode");
    private final By caseApprovalCheckbox = By.id("case_statuses_enabled");

    public AddProjectPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return projectNameInput;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getProjectName() {
        return wait.waitForVisibility(projectNameInput);
    }

    public AddProjectPage enterProjectName(String name) {
        getProjectName().sendKeys(name);
        return this;
    }

    public WebElement getProjectAnnouncement() {
        return wait.waitForVisibility(announcementTextInput);
    }

    public AddProjectPage enterProjectAnnouncement(String announcement) {
        getProjectAnnouncement().sendKeys(announcement);
        return this;
    }

    public WebElement getShowProjectAnnouncement() {
        return wait.waitForVisibility(showAnnouncementCheckbox);
    }

    public AddProjectPage setShowAnnouncement(Boolean isShown) {
        if (isShown) {
            getShowProjectAnnouncement().click();
        }
        return this;
    }

    public List<WebElement> getRadioButton() {
        return wait.waitForAllVisibleElementsLocatedBy(modeRadioButtonsList);
    }

    public AddProjectPage setProjectType(int index) {
        getRadioButton().get(index).click();
        return this;
    }

    public WebElement getCasesApproval() {
        return wait.waitForVisibility(caseApprovalCheckbox);
    }

    public AddProjectPage setCasesApproval(boolean isApprovalEnabled) {
        if (isApprovalEnabled) {
            getCasesApproval().click();
        }
        return this;
    }

    public WebElement getAddProjectButton() {
        return wait.waitForVisibility(addProjectButton);
    }

    public void clickAddProjectButton() {
        getAddProjectButton().click();
    }

}