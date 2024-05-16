package baseEntities;

import configuration.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver, boolean isOpenedByUrl) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        if (isOpenedByUrl) {
            openByUrl();
        }
    }

    protected abstract WebElement getPageIdentifier();

    protected abstract String getPagePath();

    public boolean isOpened() {

        return getPageIdentifier().isDisplayed();
    }

    public void openByUrl() {

        driver.get(ReadProperties.getUrl() + getPagePath());
    }
}