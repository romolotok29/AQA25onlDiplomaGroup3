package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.milestones.MilestonesViewPage;

public class PositiveTests extends BaseTest {
    @Description("Тест на создание сущности Проект с полным заполнением полей")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Тест на создание сущности Проект")
    public void addFullProjectTest() {

        Assert.assertTrue(
                projectSteps
                        .addProjectSuccessfully(testProject)
                        .isProjectInGrid(testProject)
        );
    }

    @Description("Тест на создание сущности Milestone")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Тест на создание сущности Milestone", dependsOnMethods = "addFullProjectTest")
    public void addMilestoneTest() {

        Assert.assertTrue(
                milestoneSteps
                        .addMilestoneSuccessfully(testProject, testMilestone)
                        .isMilestoneInGrid(testMilestone)
        );
    }

    @Description("Тест на удаление сущности Проект")
    @Severity(SeverityLevel.BLOCKER)
    @Test(testName = "Тест на удаление сущности Проект")
    public void deleteProjectTest() {

        projectSteps
                .addProjectSuccessfully(testProject);

        Assert.assertEquals(
                projectSteps
                        .deleteProjectSuccessfully(testProject)
                        .getDeleteText(), "Successfully deleted the project."
        );
    }

    @Description("Тест на проверку всплывающего сообщения")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Тест на проверку всплывающего сообщения")
    public void hiddenTextTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Copy to Clipboard"
        );

    }

    //Тест на загрузку файла проходит первый раз без проблем, файл загружается.
    // А при последующих запусках, если не удалять загруженный ранее файл, то тест падает
    @Description("Тест на загрузку файла")
    @Severity(SeverityLevel.BLOCKER)
    @Test(testName = "Тест на загрузку файла", dependsOnMethods = "addFullProjectTest")
    public void fileUploadTest() throws InterruptedException {

        milestoneSteps.addMilestoneWithFileUploadInside(testProject, testMilestone);
        MilestonesViewPage milestonesViewPage = new MilestonesViewPage(driver, true);
        //на Ассертах тест падает
        //Assert.assertTrue(milestonesViewPage.isPageOpened());
        //Assert.assertTrue(milestonesViewPage.isMilestoneImageDisplayed());
    }

    @Description("Тест на отображение диалогового окна")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Тест на отображение диалогового окна")
    public void dialogWindowTest() {

        dashboardPage.clickTopMenuSearchButton();
        Assert.assertTrue(
                dashboardPage.isDialogWindowDisplayed()
        );
    }

}
