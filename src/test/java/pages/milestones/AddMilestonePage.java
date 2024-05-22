package pages.milestones;

import baseEntities.BasePage;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.WaitsService;


public class AddMilestonePage extends BasePage {
    private final By milestoneNameInput = By.id("name");
    private final By milestoneReferenceInput = By.id("reference");
    private final By milestoneDescriptionInputLocator = By.id("description_display");
    private final By milestoneIsCompletedCheckbox = By.id("is_completed");
    private final By addMilestoneButton = By.id("accept");

    public AddMilestonePage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return milestoneNameInput;
    }

    @Override
    protected String getPagePath() {
        return null;
    }

    public WebElement getMilestoneName() {
        return wait.waitForVisibility(milestoneNameInput);
    }

    public AddMilestonePage enterMilestoneName(Milestone milestone) {
        getMilestoneName().sendKeys(milestone.getName());
        return this;
    }

    public WebElement getMilestoneReference() {
        return wait.waitForVisibility(milestoneReferenceInput);
    }

    public AddMilestonePage enterMilestoneReference(Milestone milestone) {
        getMilestoneReference().sendKeys(milestone.getReference());
        return this;
    }

    public WebElement getMilestoneDescription() {
        return wait.waitForVisibility(milestoneDescriptionInputLocator);
    }

    public AddMilestonePage enterMilestoneDescription(Milestone milestone) {
        getMilestoneDescription().sendKeys(milestone.getDescription());
        return this;
    }

    public WebElement getMilestoneCompetence() {
        return wait.waitForVisibility(milestoneIsCompletedCheckbox);
    }

    public AddMilestonePage selectMilestoneCompetence(Milestone milestone) {
        if (milestone.isCompleted()) {
            getMilestoneCompetence().click();
        }
        return this;
    }

    public WebElement getMilestoneAddButton() {
        return wait.waitForVisibility(addMilestoneButton);
    }

    public void clickAddButton() {
        getMilestoneAddButton().click();
    }

    public MilestonesOverviewPage addMilestoneSuccessfully(Milestone milestone) {
        enterMilestoneName(milestone)
                .enterMilestoneReference(milestone)
                .enterMilestoneDescription(milestone)
                .selectMilestoneCompetence(milestone)
                .clickAddButton();
        return new MilestonesOverviewPage(driver, false);
    }
}