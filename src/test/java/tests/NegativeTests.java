package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTest {

    @Test
    public void incorrectLoginTest() {

        Assert.assertEquals(
                loginSteps
                        .incorrectLogin(user, "incorrectPassword#1")
                        .showLoginErrorMessage(), "Email/Login or Password is incorrect. Please try again."
        );
    }

    @Test
    public void allureScreenshotTest() {
        
    }

}
