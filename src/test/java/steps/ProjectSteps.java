package steps;

import baseEntities.BaseSteps;
import models.Project;
import org.openqa.selenium.WebDriver;
import pages.project.AddProjectPage;
import pages.project.ProjectsOverviewPage;

public class ProjectSteps extends BaseSteps {
    public ProjectSteps(WebDriver driver) {
        super(driver);
    }

    public AddProjectPage clickAddProjectSideButton() {
        dashboardPage
                .getAddProjectSideButton()
                .click();

        return new AddProjectPage(driver, true);
    }

    public ProjectsOverviewPage addProjectSuccessfully(Project project) {
        clickAddProjectSideButton()
                .fillProjectInfo(project);

        return new ProjectsOverviewPage(driver, false);
    }

    public ProjectsOverviewPage deleteProjectSuccessfully(Project project) {
        projectsOverviewPage.clickDeleteProjectButton();
        projectsOverviewPage.clickDeleteConfirmationButton();
        projectsOverviewPage.clickDeleteOKButton();

        return projectsOverviewPage;
    }
}