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
    @Description("Тест на проверку поля для ввода на граничные значения")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на проверку граничных значений",
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

    @Description("Тест на проверку всплывающего сообщения при наведении курсора на кнопку")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Тест на проверку всплывающего сообщения")
    public void hiddenTextTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Copy to Clipboard"
        );

    }

    @Description("Тест на создание сущности Project с полным заполнением полей")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Тест на создание сущности Project")
    public void addFullProjectTest() {

        Assert.assertTrue(
                projectSteps
                        .addProjectSuccessfully(testProject)
                        .isProjectInGrid(testProject)
        );
    }

    @Description("Создание сущности Milestone с полным заполнением полей")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "addFullProjectTest", description = "Тест на создание сущности Milestone")
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

    @Description("Тест на проверку удаления сущности Project")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Тест на удаление сущности Project")
    public void deleteProjectTest() {

        projectSteps
                .addProjectSuccessfully(testProject);

        Assert.assertEquals(
                projectSteps
                        .deleteProjectSuccessfully(testProject)
                        .getDeleteText(), "Successfully deleted the project."
        );
    }

    @Description("Тест на проверку отображения диалогового окна")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Тест на отображение диалогового окна")
    public void dialogWindowTest() {

        dashboardPage.clickTopMenuSearchButton();

        Assert.assertTrue(
                dashboardPage.isDialogWindowDisplayed()
        );
    }

    //Тест на загрузку файла проходит первый раз без проблем, файл загружается.
    // А при последующих запусках, если не удалять загруженный ранее файл , то тест падает
    @Description("Тест на загрузку файла в сущность Milestone")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dependsOnMethods = {"addFullProjectTest", "addMilestoneTest"}, description = "Тест на загрузку файла")
    public void fileUploadTest() {

        milestoneSteps.uploadFileInsideMilestone(testProject, testMilestone);

        Assert.assertTrue(
                addMilestonePage.isFileDisplayedOnScreen()
        );
    }

}