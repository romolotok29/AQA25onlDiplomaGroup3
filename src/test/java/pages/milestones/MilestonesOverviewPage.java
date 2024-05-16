package pages.milestones;

import baseEntities.BasePage;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MilestonesOverviewPage extends BasePage {

    @FindBy(className = "content-header-title")
    public WebElement milestonesHeader;

    @FindBy(className = "summary-title")
    public List<WebElement> milestonesList;

    public MilestonesOverviewPage(WebDriver driver, boolean isOpenedByUrl) {
        super(driver, isOpenedByUrl);
    }

    @Override
    protected WebElement getPageIdentifier() {
        return milestonesHeader;
    }

    @Override
    protected String getPagePath() {
        return "";
    }

    public boolean isMilestoneInGrid(Milestone milestone) {
        for (WebElement element :
                milestonesList) {
            if (element.getText().trim().equals(milestone.getName())) {
                return true;
            }
        }
        return false;
    }
}