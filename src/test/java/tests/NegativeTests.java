package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    @Test(description = "Тест на ввод данных превышающих допустимое значение поля")
    public void dataExceedsTheLimitTest() {

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
