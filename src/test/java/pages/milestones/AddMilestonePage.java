package pages.milestones;

import baseEntities.BasePage;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddMilestonePage extends BasePage {
    private final static String pagePath = "";
    private final By milestoneNameInput = By.id("name");
    private final By milestoneReferenceInput = By.id("reference");
    private final By milestoneDescriptionInputLocator = By.id("description_display");
    private final By milestoneIsCompletedCheckbox = By.id("is_completed");
    private final By addMilestoneButton = By.id("accept");
    private final By uploadFileInsideMilestone = By.id("entityAttachmentListEmptyIcon");
    private final By submitAttachButton = By.id("attachmentNewSubmit");
    private final By filePreviewLocator = By.xpath("/html/body/input[3]");
    private final By selectFile = By.xpath("//div[@class='frolaAttachmentpop']/following-sibling::div/..//div[contains(@data-testid,'attachmentsTabAttachmentSelection')]");

    public AddMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return milestoneNameInput;
    }

    @Override
    protected String getPagePath() {
        return pagePath;
    }

    public WebElement getMilestoneName() {
        return wait.waitForVisibility(milestoneNameInput);
    }

    public AddMilestonePage enterMilestoneName(Milestone milestone) {
        getMilestoneName().sendKeys(milestone.getName());
        return this;
    }

    public WebElement getMilestoneReference() {
        return wait.waitForVisibility(milestoneReferenceInput);
    }

    public AddMilestonePage enterMilestoneReference(Milestone milestone) {
        getMilestoneReference().sendKeys(milestone.getReference());
        return this;
    }

    public WebElement getMilestoneDescription() {
        return wait.waitForVisibility(milestoneDescriptionInputLocator);
    }

    public AddMilestonePage enterMilestoneDescription(Milestone milestone) {
        getMilestoneDescription().sendKeys(milestone.getDescription());
        return this;
    }

    public WebElement getMilestoneCompetence() {
        return wait.waitForVisibility(milestoneIsCompletedCheckbox);
    }

    public AddMilestonePage selectMilestoneCompetence(Milestone milestone) {
        if (milestone.isCompleted()) {
            getMilestoneCompetence().click();
        }
        return this;
    }

    public WebElement getMilestoneAddButton() {
        return wait.waitForVisibility(addMilestoneButton);
    }

    public WebElement getUploadFileInsideMilestone() {
        return wait.waitForVisibility(uploadFileInsideMilestone);
    }

    public WebElement getSubmitAttachButton() {
        return wait.waitForVisibility(submitAttachButton);
    }

    public void clickSubmitAttachButton() {
        getSubmitAttachButton().click();
    }

    public WebElement getFilePreview() {
        return wait.waitForExists(filePreviewLocator);
    }

    public void clickUploadFileInsideMilestoneButton() {
        getUploadFileInsideMilestone().click();
    }

    public void clickAddMilestoneButton() {
        wait.waitForVisibility(addMilestoneButton);
        getMilestoneAddButton().click();
    }

    public AddMilestonePage fileUploadInsideMilestone() {
        clickUploadFileInsideMilestoneButton();

        String path = AddMilestonePage.class.getClassLoader().getResource("upload/quality-assurance.jpg")
                .getPath().substring(1);

        getFilePreview().sendKeys(path);

        wait.waitForElementClickable(selectFile);

        clickSubmitAttachButton();

        return this;
    }
}
