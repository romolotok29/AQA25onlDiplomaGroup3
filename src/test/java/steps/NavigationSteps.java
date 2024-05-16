package steps;

import baseEntities.BaseSteps;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;

public class NavigationSteps extends BaseSteps {
    public NavigationSteps(WebDriver driver) {
        super(driver);
    }

    public DashboardPage successLogin(User user) {
        LoginPage loginPage = new LoginPage(driver, false);
        return loginPage.successfulLogIn(user);
    }
}