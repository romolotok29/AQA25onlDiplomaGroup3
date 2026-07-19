package base_entities;
import com.google.common.collect.ImmutableMap;
import configuration.ReadProperties;
import core.BrowserService;
import models.Milestone;
import models.Project;
import models.User;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.DetailedSearchPage;
import pages.milestones.AddMilestonePage;
import steps.LoginSteps;
import steps.MilestoneSteps;
import steps.ProjectSteps;
import utils.InvokedListener;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Listeners(InvokedListener.class)
public class BaseTest {

    //private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected WebDriver driver;
    protected Faker faker = new Faker();
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
        allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("QA Engineer", "Rolands Molotoks")
                .put("OS", "Windows 11")
                .put("Version", "10.0")
                .put("Arch", "x64")
                .put("Browser", "Chrome")
                .put("Version", "150.0.7871.125")
                .put("Stand", "Test")
                .build());
    }

    @BeforeTest
    public void setUpData() {

        user = User.builder()
                .email(ReadProperties.email())
                .password(ReadProperties.password())
                .build();

        testProject = Project.builder()
                .name(faker.name().title())
                .announcement(faker.lorem().sentence())
                .isAnnouncementShown(faker.random().nextBoolean())
                .projectType(faker.random().nextInt(0, 2))
                .isApprovalEnabled(faker.random().nextBoolean())
                .build();

        testMilestone = new Milestone.Builder()
                .withName(faker.name().title())
                .withReference(faker.lorem().word())
                .withDescription(faker.lorem().sentence())
                .isMilestoneCompleted(faker.random().nextBoolean())
                .build();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional String browser, ITestContext iTestContext) {

        if (browser == null || browser.isEmpty()) {
            browser = ReadProperties.browserName();
        }

        driver = new BrowserService(browser).getDriver();
        setDriverToContext(iTestContext, driver);
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

    public static void setDriverToContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("WebDriver", driver);
    }

}