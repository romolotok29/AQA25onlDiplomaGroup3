package pages.project;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProjectsOverviewPage extends BasePage {
    private final static String pagePath = "index.php?/admin/projects/overview";
    private final By projectsGrid = By.className("grid");
    private final By projectsInGrid = By.cssSelector(".hoverSensitive");

    public ProjectsOverviewPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return projectsGrid;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public List<WebElement> getProjectInGrid() {
        return wait.waitForAllVisibleElementsLocatedBy(projectsInGrid);
    }

    public boolean isProjectInGrid(Project project) {
        for (WebElement element :
                getProjectInGrid()) {
            if (element.getText().trim().equals(project.getName())) {
                return true;
            }
        }
        return false;
    }
}