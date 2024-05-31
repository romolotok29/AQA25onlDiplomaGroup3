package tests;

import baseEntities.BaseTest;
import data.BoundaryValues;
import data.StaticProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DetailedSearchPage;

public class NegativeTests extends BaseTest {

    @Description("Тест на использование некорректных данных на странице Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест на использование некорректных данных")
    public void incorrectLoginTest() {

        Assert.assertEquals(
                loginSteps
                        .incorrectLogin(user, "incorrectPassword#1")
                        .showLoginErrorMessage(), "Email/Login or Password is incorrect. Please try again."
        );
    }

    @Description("Тест на ввод данных превышающих допустимые")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на ввод данных превышающих допустимое значение поля",
            dataProvider = "dataExceedsTheLimit", dataProviderClass = StaticProvider.class)
    public void dataExceedsTheLimitTest(String inputValue, boolean isValid) {

        detailedSearchPage = new DetailedSearchPage(driver, true);

        detailedSearchPage.boundaryValues(inputValue);

        if (!isValid) {

            switch (inputValue) {
                case "!!!" ->
                    Assert.assertEquals(
                            detailedSearchPage.getErrorText(), "Error"
                    );

                case "$$$" -> Assert.assertEquals(
                        detailedSearchPage
                                .showErrorDialogMessage(), "line 1:267 mismatched character '*' expecting '$'"
                );

                case BoundaryValues.MAX_PLUS_ONE -> Assert.assertEquals(
                        detailedSearchPage
                                .showErrorDialogMessage(), "Field Query is too long (250 characters at most)."
                );
            }
        }
    }

    @Description("Тест воспроизводящий любой дефект")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Специально падающий тест для формирования скриншота в allure report")
    public void allureScreenshotTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Incorrect expected text"
        );
    }

}
