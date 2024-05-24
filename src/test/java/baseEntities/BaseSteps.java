package baseEntities;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.project.AddProjectPage;
import pages.project.ProjectInfoPage;
import pages.project.ProjectsOverviewPage;

public class BaseSteps {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddProjectPage addProjectPage;
    protected ProjectInfoPage projectInfoPage;
    protected ProjectsOverviewPage projectsOverviewPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;

        this.loginPage = new LoginPage(driver, false);
        this.dashboardPage = new DashboardPage(driver, false);
        this.addProjectPage = new AddProjectPage(driver, true);
        this.projectInfoPage = new ProjectInfoPage(driver, false);
        this.projectsOverviewPage = new ProjectsOverviewPage(driver,false);
    }
}