package baseEntities;

import configuration.ReadProperties;
import core.WaitsService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitsService wait;

    public BasePage(WebDriver driver) {
        this(driver, false);
    }

    public BasePage(WebDriver driver, boolean openPageByUrl) {
        this.driver = driver;
        this.wait = new WaitsService(driver);

        if (openPageByUrl) {
            openPageByUrl();
        }
    }

    protected abstract By getPageIdentifier();
    protected abstract String getPagePath();

    public boolean isPageOpened() {
        return wait.waitForVisibility(getPageIdentifier()).isDisplayed();
    }

    public void openPageByUrl() {
        driver.get(ReadProperties.getUrl() + getPagePath());
    }

    public String moveToElement(WebElement element) {
        Actions actions = new Actions(driver);

        actions
                .moveToElement(element)
                .build()
                .perform();

        String text = String.valueOf(element);

        return text;
    }

}