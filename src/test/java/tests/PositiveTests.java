package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

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
        DashboardPage dashboardPage = new DashboardPage(driver, false);

        Assert.assertEquals(
                dashboardPage.moveToElement(), "Copy to Clipboard"
        );
    }

    // Почему-то выкидывает ElementNotInteractableException: element not interactable
    // Тест падает на 135 строчке кода (AddMilestonePage, метод fileUploadInsideMilestone())
    // Может я использую неверный локатор для .sendkeys(path)?
    @Test
    public void fileUploadTest() {

        Assert.assertTrue(
                milestoneSteps
                        .addMilestoneWithFileUploadInside(testProject, testMilestone)
                        .isMilestoneInGrid(testMilestone)
        );
    }
}
