package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailedSearchPage extends BasePage {

    private static final String pagePath = "/index.php?/new_search/results&queries[]=a";
    private final By searchButtonLocator = By.cssSelector("#searchQueryDetailed");
    private final By searchItemsBoxLocator = By.cssSelector(".search-item");
    private final By clearAllButtonLocator = By.cssSelector(".search-item-clear-all");
    private final By errorTitleLocator = By.xpath("//span[contains(text(), 'Error')]");
    private final By errorDialogMessage = By.xpath("//p[@data-testid='deleteAttachmentDialogMessage']");

    public DetailedSearchPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    @Override
    protected By getPageIdentifier() {
        return searchButtonLocator;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getSearchButton() {
        return wait.waitForVisibility(searchButtonLocator);
    }

    public WebElement getClearAllButton() {
        return wait.waitForVisibility(clearAllButtonLocator);
    }

    public void clickOnClearAllButton() {
        getClearAllButton().click();
    }

    public void clickOnSearchButton() {
        getSearchButton().click();
    }

    public WebElement getErrorTitle() {
        return wait.waitForVisibility(errorTitleLocator);
    }

    public String getErrorText() {
        return getErrorTitle().getText();
    }

    public WebElement getErrorDialogMessage() {
        return wait.waitForVisibility(errorDialogMessage);
    }

    public String showErrorDialogMessage() {
        return getErrorDialogMessage().getText();
    }

    public WebElement getSearchItemsBox() {
        return wait.waitForVisibility(searchItemsBoxLocator);
    }

    public boolean isErrorDisplayed() {
        return getErrorTitle().isDisplayed();
    }

    public boolean isSearchItemsBoxShown() {
        return getSearchItemsBox().isDisplayed();
    }

    public void boundaryValues(String inputValue) {
        clickOnClearAllButton();
        clickOnSearchButton();
        getSearchButton().sendKeys(inputValue);
        getSearchButton().sendKeys(Keys.ENTER);
    }

}
