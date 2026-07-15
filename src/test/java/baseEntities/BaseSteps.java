package baseEntities;

import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.IntegrationPage;
import pages.LoginPage;
import pages.milestones.AddMilestonePage;
import pages.milestones.MilestonesOverviewPage;
import pages.project.AddProjectPage;
import pages.project.ProjectInfoPage;
import pages.project.ProjectsOverviewPage;

@Slf4j(access = AccessLevel.PROTECTED)
public class BaseSteps {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddProjectPage addProjectPage;
    protected ProjectInfoPage projectInfoPage;
    protected ProjectsOverviewPage projectsOverviewPage;
    protected AddMilestonePage addMilestonePage;
    protected MilestonesOverviewPage milestonesOverviewPage;
    protected IntegrationPage integrationPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;

        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver, false);
        this.addProjectPage = new AddProjectPage(driver);
        this.projectInfoPage = new ProjectInfoPage(driver);
        this.projectsOverviewPage = new ProjectsOverviewPage(driver);
        this.addMilestonePage = new AddMilestonePage(driver);
        this.milestonesOverviewPage = new MilestonesOverviewPage(driver);
        this.integrationPage = new IntegrationPage(driver, false);
    }

}