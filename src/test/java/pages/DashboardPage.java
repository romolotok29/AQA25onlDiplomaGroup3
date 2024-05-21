package pages;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.project.AddProjectPage;
import pages.project.ProjectInfoPage;

import java.util.List;

public class DashboardPage extends BasePage {
    private final static String pagePath = "/index.php?/dashboard";
    private final By addProjectSideButton = By.id("sidebar-projects-add");
    private final By projectsOnDashboard = By.xpath("//a[text()='AQA_25_Test']");


    public DashboardPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return addProjectSideButton;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }


    public WebElement getAddProjectSideButton() {
        return wait.waitForVisibility(addProjectSideButton);
    }

    public AddProjectPage clickAddProjectSideButton() {
        getAddProjectSideButton().click();
        return new AddProjectPage(driver, true);
    }

    // правильно ли? return (List<WebElement>) wait.waitForVisibility(projectsInGrid);
    public List<WebElement> getProjectInGrid() {
        return (List<WebElement>) wait.waitForVisibility(projectsOnDashboard);
    }

    public ProjectInfoPage clickProjectInGrid(Project project) {
        for (WebElement element :
                getProjectInGrid()) {
            if (element.getText().trim().equals(project.getName())) {
                element.click();
                return new ProjectInfoPage(driver, false);
            }
        }
        return null;
    }

}