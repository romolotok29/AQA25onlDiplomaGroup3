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

    public AddMilestonePage clickAddMilestoneSideButton() {
        projectInfoPage
                .getAddMilestoneToProject()
                .click();

        logger.info("Adding a milestone...");
        return addMilestonePage;
    }

    public void fillMilestoneInfo(Milestone milestone) {
        addMilestonePage
                .enterMilestoneName(milestone)
                .enterMilestoneReference(milestone)
                .enterMilestoneDescription(milestone)
                .selectMilestoneCompetence(milestone)
                .clickAddMilestoneButton();
    }

    public MilestonesOverviewPage addMilestoneSuccessfully(Project project, Milestone milestone) {
        dashboardPage.clickOnProjectInGrid(project);

        clickAddMilestoneSideButton();
        fillMilestoneInfo(milestone);

        logger.info("Milestone added successfully!");
        return milestonesOverviewPage;
    }

    public void fillMilestoneInfoWithFileUpload(Milestone milestone) {
        addMilestonePage
                .enterMilestoneName(milestone)
                .enterMilestoneReference(milestone)
                .enterMilestoneDescription(milestone)
                .selectMilestoneCompetence(milestone)
                .fileUploadInsideMilestone();
    }

    public void uploadFileInsideMilestone(Project project, Milestone milestone) {
        dashboardPage.clickOnProjectInGrid(project);

        clickAddMilestoneSideButton();
        fillMilestoneInfoWithFileUpload(milestone);

        logger.info("File uploaded successfully!");
    }
}
