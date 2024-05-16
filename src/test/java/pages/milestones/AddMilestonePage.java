package pages.milestones;

import baseEntities.BasePage;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddMilestonePage extends BasePage {
    @FindBy(id = "name")
    public WebElement milestoneNameInput;
    @FindBy(id = "reference")
    public WebElement milestoneReferenceInput;
    @FindBy(id = "is_completed")
    public WebElement milestoneIsCompletedCheckbox;
    @FindBy(id = "accept")
    public WebElement addMilestoneButton;

    public AddMilestonePage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected WebElement getPageIdentifier() {
        return milestoneNameInput;
    }

    @Override
    protected String getPagePath() {
        return null;
    }

    public AddMilestonePage enterMilestoneName(Milestone milestone) {
        milestoneNameInput.sendKeys(milestone.getName());
        return this;
    }

    public AddMilestonePage enterMilestoneReference(Milestone milestone) {
        milestoneReferenceInput.sendKeys(milestone.getReference());
        return this;
    }

    public AddMilestonePage selectMilestoneCompetence(Milestone milestone) {
        if (milestone.isCompleted()) {
            milestoneIsCompletedCheckbox.click();
        }
        return this;
    }

    public void clickAddButton() {
        addMilestoneButton.click();
    }

    public MilestonesOverviewPage addMilestoneSuccessfully(Milestone milestone) {
        enterMilestoneName(milestone)
                .enterMilestoneReference(milestone)
                .selectMilestoneCompetence(milestone)
                .clickAddButton();
        return new MilestonesOverviewPage(driver, false);
    }
}