package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.milestones.MilestonesViewPage;

public class PositiveTests extends BaseTest {

    @Test
    public void addFullProjectTest() {

        Assert.assertTrue(
                projectSteps
                        .addProjectSuccessfully(testProject)
                        .isProjectInGrid(testProject)
        );
    }

    @Test
    public void addMilestoneTest() {

        Assert.assertTrue(
                milestoneSteps
                        .addMilestoneSuccessfully(testProject, testMilestone)
                        .isMilestoneInGrid(testMilestone)
        );
    }

    @Test
    public void deleteProjectTest() {

        projectSteps
                .addProjectSuccessfully(testProject);

        Assert.assertEquals(
                projectSteps
                        .deleteProjectSuccessfully(testProject)
                        .getDeleteText(), "Successfully deleted the project."
        );
    }

    @Test
    public void hiddenTextTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Copy to Clipboard"
        );

    }

    //Загружается картинка, только после этого открывается проводник и тест падает. Может быть неправильный локатор?
    @Test
    public void fileUploadTest() {

        milestoneSteps.addMilestoneWithFileUploadInside(testProject, testMilestone);

        MilestonesViewPage milestonesViewPage = new MilestonesViewPage(driver, true);

        Assert.assertTrue(
                milestonesViewPage.isPageOpened()
        );

        Assert.assertTrue(
                milestonesViewPage.isMilestoneImageDisplayed()
        );
    }

    @Test
    public void dialogWindowTest() {

        dashboardPage.clickTopMenuSearchButton();

        Assert.assertTrue(
                dashboardPage.isDialogWindowDisplayed()
        );
    }
    
}
