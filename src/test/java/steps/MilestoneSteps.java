package steps;

import baseEntities.BaseSteps;
import models.Milestone;
import models.Project;
import org.openqa.selenium.WebDriver;
import pages.milestones.AddMilestonePage;
import pages.milestones.MilestonesOverviewPage;

public class MilestoneSteps extends BaseSteps {

    public MilestoneSteps(WebDriver driver) {
        super(driver);
    }

    public AddMilestonePage clickAddMilestoneButton() {
        projectInfoPage
                .getAddMilestoneToProject()
                .click();

        return new AddMilestonePage(driver, false);
    }

    public MilestonesOverviewPage addMilestoneSuccessfully(Project project, Milestone milestone) {
        dashboardPage.clickOnProjectInGrid(project);

        clickAddMilestoneButton()
                .fillMilestoneInfo(milestone);

        return new MilestonesOverviewPage(driver, false);
    }

    public MilestonesOverviewPage addMilestoneWithFileUploadInside(Project project, Milestone milestone) {
        dashboardPage.clickOnProjectInGrid(project);

        clickAddMilestoneButton()
                .fillMilestoneInfoWithFileUpload(milestone);

        return new MilestonesOverviewPage(driver, false);
    }
}
