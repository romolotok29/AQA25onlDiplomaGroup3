package steps;

import base_entities.BaseSteps;
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

        return addProjectPage;
    }

    public void fillProjectInfo(Project project) {
        addProjectPage
                .enterProjectName(project.getName())
                .enterProjectAnnouncement(project.getAnnouncement())
                .setShowAnnouncement(project.isAnnouncementShown())
                .setProjectType(project.getProjectType())
                .setCasesApproval(project.isApprovalEnabled())
                .clickAddProjectButton();
    }

    public ProjectsOverviewPage addProjectSuccessfully(Project project) {
        clickAddProjectSideButton();
        fillProjectInfo(project);

        log.info("Project added successfully!");
        return projectsOverviewPage;
    }

    public ProjectsOverviewPage deleteProjectSuccessfully(Project project) {
        projectsOverviewPage.clickDeleteProjectButton();
        projectsOverviewPage.clickDeleteConfirmationButton();
        projectsOverviewPage.clickDeleteOKButton();

        log.info("Project deleted successfully!");
        return projectsOverviewPage;
    }

}