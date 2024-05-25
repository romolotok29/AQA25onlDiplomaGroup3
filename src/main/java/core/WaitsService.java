package core;

import configuration.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitsService {
    private WebDriverWait wait;
    private WebDriver driver;
    private Duration timeout;

    public WaitsService(WebDriver driver) {
        this.driver = driver;
        timeout = Duration.ofSeconds(ReadProperties.timeout());
        wait = new WebDriverWait(driver, timeout);
    }

    public WaitsService(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
        wait = new WebDriverWait(driver, timeout);
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForAllVisibleElementsLocatedBy(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

}
