package core;

import configuration.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WaitsService {
    private WebDriverWait wait;
    private WebDriver driver;
    private Duration timeout;

    public WaitsService(WebDriver driver) {
        this.driver = driver;
        timeout = Duration.ofSeconds(ReadProperties.timeout());
        wait = new WebDriverWait(driver, this.timeout);
    }

    public WaitsService(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
        wait = new WebDriverWait(driver, timeout);
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForExists(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementToBecomeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public List<WebElement> waitForAllVisibleElementsLocatedBy(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    //Добавить правильную обработку Exception
    public WebElement fluentWaitForElementVisibility(By locator) {
        try {
            FluentWait<WebDriver> fluent = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(50))
                    .ignoring(NoSuchElementException.class);

            return fluent.until(driver -> waitForVisibility(locator));

        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be visible: " + e.getMessage());
            throw new RuntimeException("Error occurred while waiting for element", e);
        }
    }

    public List<WebElement> fluentWaitForAllElementsVisibility(By locator) {
        FluentWait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class);

        return fluent.until(driver -> waitForAllVisibleElementsLocatedBy(locator));
    }

    /*
    public Boolean waitForAllElementsInvisibility(List<WebElement> webElement) {
        return wait.until(ExpectedConditions.invisibilityOfAllElements(webElement));
    }

    public Boolean fluentWaitForAllElementsInvisibility(By locator) {
        FluentWait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(RuntimeException.class);

        return (Boolean) fluent.until(driver -> waitForAllVisibleElementsLocatedBy(locator).isEmpty() ? true : new RuntimeException());
    }

     */

}