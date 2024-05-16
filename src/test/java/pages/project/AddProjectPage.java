package pages.project;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddProjectPage extends BasePage {
    private final static String pagePath = "/index.php?/admin/projects/add/1";

    @FindBy(id = "name")
    public WebElement projectNameInput;
    @FindBy(id = "accept")
    public WebElement addProjectButton;

    @FindBy(id = "announcement_display")
    public WebElement announcementTextInput;

    @FindBy(id = "show_announcement")
    public WebElement showAnnouncementCheckbox;

    @FindBy(name = "suite_mode")
    public List<WebElement> modeRadioButtonsList;

    @FindBy(id = "case_statuses_enabled")
    public WebElement caseApprovalCheckbox;

    public AddProjectPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected WebElement getPageIdentifier() {
        return projectNameInput;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public AddProjectPage enterProjectName(String name) {
        projectNameInput.sendKeys(name);
        return this;
    }

    public AddProjectPage enterProjectAnnouncement(String announcement) {
        announcementTextInput.sendKeys(announcement);
        return this;
    }

    public AddProjectPage setShowAnnouncement(Boolean isShown) {
        if (isShown) {
            showAnnouncementCheckbox.click();
        }
        return this;
    }

    public AddProjectPage setProjectType(int index) {
        modeRadioButtonsList.get(index).click();
        return this;
    }

    public AddProjectPage setCasesApproval(boolean isApprovalEnabled) {
        if (isApprovalEnabled) {
            caseApprovalCheckbox.click();
        }
        return this;
    }

    public void clickAddProjectButton() {
        addProjectButton.click();
    }

    public ProjectsOverviewPage addSimpleProject(Project project) {
        enterProjectName(project.getName())
                .clickAddProjectButton();
        return new ProjectsOverviewPage(driver, true);
    }

    public ProjectsOverviewPage addFullProject(Project project) {
        this.enterProjectAnnouncement(project.getAnnouncement())
                .setShowAnnouncement(project.isAnnouncementShown())
                .setProjectType(project.getProjectType())
                .setCasesApproval(project.isApprovalEnabled())
                .addSimpleProject(project);
        return new ProjectsOverviewPage(driver, true);
    }
}