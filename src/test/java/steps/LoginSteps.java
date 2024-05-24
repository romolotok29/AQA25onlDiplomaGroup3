package steps;

import baseEntities.BaseSteps;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;

public class LoginSteps extends BaseSteps {
    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public DashboardPage successfulLogin(User user) {
        loginPage.fillUserInfo(user);

        return new DashboardPage(driver, false);
    }
}