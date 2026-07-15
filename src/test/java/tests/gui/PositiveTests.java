package tests.gui;

import baseEntities.BaseTest;
import data.StaticProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.DetailedSearchPage;

public class PositiveTests extends BaseTest {
    @Description("Check the Search input field for boundary values")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Boundary values", description = "Search input boundary values",
            dataProvider = "boundaryValues", dataProviderClass = StaticProvider.class)
    public void boundaryValuesTest(String inputValue, boolean isValid) {

        detailedSearchPage = new DetailedSearchPage(driver, true);

        detailedSearchPage.boundaryValues(inputValue);

        if (isValid) {

            Assert.assertTrue(
                    detailedSearchPage.isSearchItemsBoxShown()
            );
        }
    }

    @Description("Test to check pop-up message when the cursor hovers over a button")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Pop-up window check", description = "Pop-up window check")
    public void hiddenTextTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Copy to Clipboard"
        );

    }

    @Description("Project entity creation test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Create a Project entity", description = "Create a Project entity")
    public void addFullProjectTest() {

        Assert.assertTrue(
                projectSteps
                        .addProjectSuccessfully(testProject)
                        .isProjectInGrid(testProject)
        );
    }

    @Description("Milestone entity creation test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Create a Milestone entity", description = "Create a Milestone entity",
            dependsOnMethods = "addFullProjectTest")
    public void addMilestoneTest() {

        Assert.assertTrue(
                milestoneSteps
                        .addMilestoneSuccessfully(testProject, testMilestone)
                        .isMilestoneInGrid(testMilestone)
        );

        DashboardPage dashboardPage = new DashboardPage(driver, true);

        Assert.assertTrue(
                dashboardPage.isPageOpened()
        );

    }

    @Description("Project entity delete test")
    @Severity(SeverityLevel.BLOCKER)
    @Test(testName = "Delete a Project entity", description = "Delete a Project entity")
    public void deleteProjectTest() {

        projectSteps
                .addProjectSuccessfully(testProject);

        Assert.assertEquals(
                projectSteps
                        .deleteProjectSuccessfully(testProject)
                        .getDeleteText(), "Successfully deleted the project."
        );
    }

    @Description("Test to check if dialog window is displayed")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Display a dialog window", description = "Display a dialog window")
    public void dialogWindowTest() {

        dashboardPage.clickTopMenuSearchButton();

        Assert.assertTrue(
                dashboardPage.isDialogWindowDisplayed()
        );
    }

    @Description("Test to load file into a Milestone entity")
    @Severity(SeverityLevel.BLOCKER)
    @Test(testName = "Load file into a Milestone", description = "Load file into a Milestone",
            dependsOnMethods = {"addFullProjectTest", "addMilestoneTest"})
    public void fileUploadTest() {

        milestoneSteps.uploadFileInsideMilestone(testProject, testMilestone);

        Assert.assertTrue(
                addMilestonePage.isFileDisplayedOnScreen()
        );
    }

}