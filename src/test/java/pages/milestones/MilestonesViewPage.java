package pages.milestones;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MilestonesViewPage extends BasePage {

    private final static String pagePath = "/index.php?/milestones/view/";
    private final By headerTitleLocator = By.xpath("//div[contains(text(), 'Testing Milestone')]");
    private final By milestoneImageLocator = By.className("content-header-title");

    public MilestonesViewPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return headerTitleLocator;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getMilestoneImage() {
        return wait.waitForVisibility(milestoneImageLocator);
    }

    public boolean isMilestoneImageDisplayed() {
        if (getMilestoneImage().isDisplayed()) {
            return true;
        }
        else return false;
    }
}
