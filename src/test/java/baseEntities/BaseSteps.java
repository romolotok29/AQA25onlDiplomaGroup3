package baseEntities;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.milestones.AddMilestonePage;
import pages.milestones.MilestonesOverviewPage;
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
    protected AddMilestonePage addMilestonePage;
    protected MilestonesOverviewPage milestonesOverviewPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;

        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.addProjectPage = new AddProjectPage(driver);
        this.projectInfoPage = new ProjectInfoPage(driver);
        this.projectsOverviewPage = new ProjectsOverviewPage(driver);
        this.addMilestonePage = new AddMilestonePage(driver);
        this.milestonesOverviewPage = new MilestonesOverviewPage(driver);
    }

}