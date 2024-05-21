package pages.project;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.milestones.AddMilestonePage;


public class ProjectInfoPage extends BasePage {
    private final By projectTitle = By.cssSelector(".content-header-title");
    private final By addMilestoneLink = By.id("sidebar-milestones-add");

    public ProjectInfoPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return projectTitle;
    }

    @Override
    protected String getPagePath() {
        return "";
    }


    public WebElement getAddMilestoneToProject() {
        return wait.waitForVisibility(addMilestoneLink);
    }

    public AddMilestonePage clickAddMilestoneToProject() {
        getAddMilestoneToProject().click();
        return new AddMilestonePage (driver, false);
    }

    // нужен ли тут getprojectTitle если он нигде не используется?
    public WebElement getprojectTitle() {
        return wait.waitForVisibility(projectTitle);
    }
}