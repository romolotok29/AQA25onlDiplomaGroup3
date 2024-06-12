package pages.project;

import baseEntities.BasePage;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProjectsOverviewPage extends BasePage {
    private final static String pagePath = "";
    private final By projectsGrid = By.className("grid");
    private final By projectsInGrid = By.cssSelector(".hoverSensitive");
    private final By deleteProjectButton = By.cssSelector(".icon-small-delete");
    private final By deleteConfirmationButton = By.xpath
            ("//strong[contains(text(), 'Yes, delete this project (cannot be undone)')]");
    private final By deleteOKButton = By.xpath("//a[@data-testid='caseFieldsTabDeleteDialogButtonOk']");
    private final By successfulDeleteMessage = By.cssSelector(".message-success");

    public ProjectsOverviewPage(WebDriver driver) {
        super(driver);
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

    public WebElement getDeleteProjectButton() {
        return wait.waitForVisibility(deleteProjectButton);
    }

    public void clickDeleteProjectButton() {
        getDeleteProjectButton().click();
    }

    public WebElement getDeleteConfirmationButton() {
        return wait.waitForVisibility(deleteConfirmationButton);
    }

    public void clickDeleteConfirmationButton() {
        getDeleteConfirmationButton().click();
    }

    public WebElement getDeleteOKButton() {
        return wait.waitForVisibility(deleteOKButton);
    }

    public void clickDeleteOKButton() {
        getDeleteOKButton().click();
    }

    public WebElement getSuccessfulDeleteMessage() {
        return wait.waitForVisibility(successfulDeleteMessage);
    }

    public String getDeleteText() {
        return getSuccessfulDeleteMessage().getText();
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