package baseEntities;

import configuration.ReadProperties;
import core.WaitsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitsService wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {
        this(driver, false);
    }

    public BasePage(WebDriver driver, boolean openPageByUrl) {
        this.driver = driver;
        this.wait = new WaitsService(driver);
        this.logger = LogManager.getLogger(BasePage.class);

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

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);

        actions
                .moveToElement(element)
                .build()
                .perform();

    }

    public String correctFilePath(String filePath) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win") && filePath.startsWith("/")) {
            filePath = filePath.substring(1);

        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            logger.info("There is nothing to change for Mac, Unix and Linux.");
        }
        return filePath;
    }
}