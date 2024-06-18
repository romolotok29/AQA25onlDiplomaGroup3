package baseEntities;

import configuration.ReadProperties;
import core.BrowsersService;
import models.Milestone;
import models.Project;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.DetailedSearchPage;
import pages.milestones.AddMilestonePage;
import steps.LoginSteps;
import steps.MilestoneSteps;
import steps.ProjectSteps;
import utils.FileUtil;
import utils.InvokedListener;

import java.io.IOException;

@Listeners(InvokedListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected User user;
    protected Project testProject;
    protected Milestone testMilestone;
    protected LoginSteps loginSteps;
    protected ProjectSteps projectSteps;
    protected MilestoneSteps milestoneSteps;
    protected DashboardPage dashboardPage;
    protected AddMilestonePage addMilestonePage;
    protected DetailedSearchPage detailedSearchPage;

    @BeforeSuite
    public void setAllureEnvironment() {
        try {
            FileUtil.copyEnvironmentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUpData() {
        user = User
                .builder()
                .email(ReadProperties.email())
                .password(ReadProperties.password())
                .build();

        testProject = Project
                .builder()
                .name("AQA_25_Test")
                .announcement("Project announcement")
                .isAnnouncementShown(true)
                .projectType(0)
                .isApprovalEnabled(true)
                .build();

        testMilestone = new Milestone.Builder()
                .withName("TestMilestone")
                .withReference("Reference")
                .withDescription("Description")
                .isMilestoneCompleted(false)
                .build();
    }

    @BeforeMethod
    public void setUp(ITestContext iTestContext) {
        driver = new BrowsersService().getDriver();
        this.setDriverToContext(iTestContext, driver);
        driver.get(ReadProperties.getUrl());
        loginSteps = new LoginSteps(driver);
        loginSteps.successfulLogin(user);
        projectSteps = new ProjectSteps(driver);
        milestoneSteps = new MilestoneSteps(driver);
        dashboardPage = new DashboardPage(driver, false);
        addMilestonePage = new AddMilestonePage(driver);
        detailedSearchPage = new DetailedSearchPage(driver, false);

    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    public static void setDriverToContext(ITestContext iTestContext, WebDriver driver){
        iTestContext.setAttribute("WebDriver", driver);
    }

}