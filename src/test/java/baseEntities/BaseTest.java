package baseEntities;

import configuration.ReadProperties;
import models.Milestone;
import models.Project;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import core.BrowserService;
import steps.NavigationSteps;

public class BaseTest {
    protected WebDriver driver;
    protected User user;
    protected Project testProject;
    protected Milestone testMilestone;

    @BeforeTest
    public void setUpData() {
        user = new User();
        user.setEmail(ReadProperties.username());
        user.setPassword(ReadProperties.password());
        testProject = new Project();
        testProject.setName("AQA_25_Test");
        testProject.setAnnouncement("announcement");
        testProject.setIsAnnouncementShown(true);
        testProject.setProjectType(0);
        testProject.setIsApprovalEnabled(false);
        testMilestone = new Milestone.Builder()
                .withName("TestMilestone")
                .withReference("Reference")
                .isMilestoneCompleted(false)
                .build();
    }

    @BeforeMethod
    public void setUp() {
        driver = new BrowserService().getDriver();
        driver.get(ReadProperties.getUrl());
        NavigationSteps navigationSteps = new NavigationSteps(driver);
        navigationSteps.successLogin(user);
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
