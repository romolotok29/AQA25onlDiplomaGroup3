package pages;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.project.AddProjectPage;
import pages.project.ProjectInfoPage;

import java.util.List;

public class DashboardPage extends BasePage {
    private final static String pagePath = "/index.php?/dashboard";

    @FindBy(id = "sidebar-projects-add")
    public WebElement addProjectSideButton;

    @FindBy(xpath = "//a[text()='AQA_25_Test']")
    public List<WebElement> projectsOnDashboard;


    public DashboardPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected WebElement getPageIdentifier() {
        return addProjectSideButton;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public AddProjectPage clickAddProjectSideButton() {
        addProjectSideButton.click();
        return new AddProjectPage(driver, true);
    }

    public ProjectInfoPage clickProjectInGrid(Project project) {

        for (WebElement element :
                projectsOnDashboard) {
            if (element.getText().trim().equals(project.getName())) {
                element.click();
                return new ProjectInfoPage(driver, false);
            }
        }
        return null;
    }

}