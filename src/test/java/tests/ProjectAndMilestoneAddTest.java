package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

public class ProjectAndMilestoneAddTest extends BaseTest {

    @Test
    public void addSimpleProjectTest() {
        DashboardPage dashboardPage = new DashboardPage(driver, true);
        Assert.assertTrue(
                dashboardPage
                        .clickAddProjectSideButton()
                        .addSimpleProject(testProject)
                        .isProjectInGrid(testProject));
    }

    @Test
    public void addFullProjectTest() {
        DashboardPage dashboardPage = new DashboardPage(driver, true);
        Assert.assertTrue(
                dashboardPage
                        .clickAddProjectSideButton()
                        .addFullProject(testProject)
                        .isProjectInGrid(testProject));
    }

    @Test(dependsOnMethods = "addFullProjectTest")
    public void addMilestoneTest() {
        DashboardPage dashboardPage = new DashboardPage(driver, true);
        Assert.assertTrue(dashboardPage.clickProjectInGrid(testProject)
                .clickAddMilestoneToProject()
                .addMilestoneSuccessfully(testMilestone)
                .isMilestoneInGrid(testMilestone));
    }
}