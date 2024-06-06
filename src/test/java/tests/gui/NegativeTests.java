package tests.gui;

import baseEntities.BaseTest;
import data.StaticProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DetailedSearchPage;

import static data.StaticProvider.MAX_PLUS_ONE;

public class NegativeTests extends BaseTest {
    @Description("Тест на использование некорректных данных на странице Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Использование некорректных данных", description = "Использование некорректных данных")
    public void incorrectLoginTest() {

        Assert.assertEquals(
                loginSteps
                        .incorrectLogin(user, "incorrectPassword#1")
                        .showLoginErrorMessage(), "Email/Login or Password is incorrect. Please try again."
        );
    }

    @Description("Тест на ввод данных превышающих допустимое значение поля")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Ввод данных превышающих допустимые", description = "Ввод данных превышающих допустимые",
            dataProvider = "dataExceedsTheLimit", dataProviderClass = StaticProvider.class)
    public void dataExceedsTheLimitTest(String inputValue, boolean isValid) {

        detailedSearchPage = new DetailedSearchPage(driver, true);

        detailedSearchPage.boundaryValues(inputValue);

        if (inputValue.equals("!!!") && !isValid) {

            Assert.assertEquals(
                    detailedSearchPage.getErrorText(), "Error"
            );

        } else if (inputValue.equals("$$$") && !isValid) {

            Assert.assertTrue(
                    detailedSearchPage.isErrorDisplayed()
            );

        } else if (inputValue.equals(MAX_PLUS_ONE) && !isValid) {

            Assert.assertEquals(
                    detailedSearchPage
                            .showErrorDialogMessage(), "Field Query is too long (250 characters at most)."
            );
        }
    }

    @Description("Специально падающий тест для формирования скриншота в allure report")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Воспроизведение любого дефекта", description = "Воспроизведение любого дефекта")
    public void allureScreenshotTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Incorrect expected text"
        );
    }

}
