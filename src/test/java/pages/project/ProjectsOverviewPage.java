package pages.project;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProjectsOverviewPage extends BasePage {
    private final static String pagePath = "index.php?/admin/projects/overview";
    @FindBy(className = "grid")
    public WebElement projectsGrid;

    @FindBy(css = ".hoverSensitive")
    public List<WebElement> projectsInGrid;

    public ProjectsOverviewPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected WebElement getPageIdentifier() {
        return projectsGrid;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public boolean isProjectInGrid(Project project) {
        for (WebElement element :
                projectsInGrid) {
            if (element.getText().trim().equals(project.getName())) {
                return true;
            }
        }
        return false;
    }
}