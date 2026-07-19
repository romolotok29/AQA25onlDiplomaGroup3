package tests.ui;

import base_entities.BaseTest;
import data.StaticProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DetailedSearchPage;

import static data.StaticProvider.MAX_PLUS_ONE;

@Story("")
public class NegativeTests extends BaseTest {

    @Feature("")
    @Description("Invalid login credentials should be rejected with appropriate message")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Use of incorrect data to log in", description = "Use of incorrect data to log in")
    public void incorrectLoginTest() {

        Assert.assertEquals(
                loginSteps
                        .incorrectLogin(user, "incorrectPassword#1")
                        .showLoginErrorMessage(), "Email/Login or Password is incorrect. Please try again."
        );
    }

    @Feature("")
    @Description("Entering data greater than field allows/Entering data that field not allows test")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Entering data greater than field allows", description = "Entering data greater than field allows",
            dataProvider = "inputDataExceedsTheLimit", dataProviderClass = StaticProvider.class)
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

    @Description("Special error playback to demonstrate a screenshot generation in allure report when test fails")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Special error playback", description = "Special error playback")
    public void allureScreenshotTest() {

        dashboardPage.moveToElement(dashboardPage.copyToClipboardButton());

        Assert.assertEquals(
                dashboardPage.showCopyToClipboardHiddenText(), "Incorrect expected text."
        );
    }

}