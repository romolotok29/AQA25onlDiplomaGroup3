package core;

import configuration.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

public class BrowserService {
    private WebDriver driver = null;
    private DriverManagerType driverManagerType;

    public BrowserService(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            case "edge":
                driverManagerType = DriverManagerType.EDGE;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new EdgeDriver(getEdgeOptions());
                break;
            case "safari":
                driverManagerType = DriverManagerType.SAFARI;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new SafariDriver(getSafariOptions());
            default:
                System.out.println("Browser " + ReadProperties.browserName() + " is not supported");
                break;
        }
    }

    public WebDriver getDriver() {
        driver.manage().deleteAllCookies();
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--silent");
        chromeOptions.addArguments("--incognito");
        //chromeOptions.addArguments("--headless");

        return chromeOptions;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.getBrowserVersion();
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.addArguments("--silent");
        edgeOptions.addArguments("--incognito");
        //edgeOptions.addArguments("--headless");

        return edgeOptions;
    }

    private SafariOptions getSafariOptions() {
        SafariOptions safariOptions = new SafariOptions();

        // Очищает сессию браузера после каждого теста
        safariOptions.setCapability("safari.cleanSession", true);

        // Использовать обычный Safari или Safari Technology Preview (если true, то используется Preview версия)
        safariOptions.setUseTechnologyPreview(false);

        // Включение режима автоматизации (иногда нужно для запуска тестов)
        safariOptions.setCapability("safari.automaticInspection", false);

        // Отключение автоматической паузы для отладки (Safari может автоматически приостанавливать выполнение для отладки)
        safariOptions.setCapability("safari.automaticProfiling", false);

        // Установка таймаута команд (опционально, если нужно настроить таймауты для команд WebDriver)
        //safariOptions.setCapability("timeouts", ImmutableMap.of("implicit", 5000));

        return safariOptions;
    }

}