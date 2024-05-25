package baseEntities;

import configuration.ReadProperties;
import core.BrowsersService;
import models.Milestone;
import models.Project;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import steps.LoginSteps;
import steps.MilestoneSteps;
import steps.ProjectSteps;

public class BaseTest {

    protected WebDriver driver;
    protected User user;
    protected Project testProject;
    protected Milestone testMilestone;
    protected LoginSteps loginSteps;
    protected ProjectSteps projectSteps;
    protected MilestoneSteps milestoneSteps;

    @BeforeTest
    public void setUpData() {
        user = User
                .builder()
                .email(ReadProperties.username())
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
    public void setUp() {
        driver = new BrowsersService().getDriver();
        driver.get(ReadProperties.getUrl());
        loginSteps = new LoginSteps(driver);
        loginSteps.successfulLogin(user);
        projectSteps = new ProjectSteps(driver);
        milestoneSteps = new MilestoneSteps(driver);
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
