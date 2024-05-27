package steps;

import baseEntities.BaseSteps;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {
    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public void fillUserInfo(User user) {
        loginPage
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLoginButton();
    }

    public DashboardPage successfulLogin(User user) {
        fillUserInfo(user);

        return new DashboardPage(driver);
    }

    public void fillUserWithIncorrectInfo(User user, String password) {
        loginPage
                .enterEmail(user.getEmail())
                .enterPassword(password)
                .clickLoginButton();
    }

    public LoginPage incorrectLogin(User user, String password) {
        dashboardPage.clickTopMenuUserButton();
        dashboardPage.clickTopMenuUserLogoutButton();

        fillUserWithIncorrectInfo(user, password);

        return loginPage;
    }

}