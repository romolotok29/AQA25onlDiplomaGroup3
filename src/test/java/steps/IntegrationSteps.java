package steps;

import baseEntities.BaseSteps;
import models.Integration;
import org.openqa.selenium.WebDriver;

public class IntegrationSteps extends BaseSteps {

    public IntegrationSteps(WebDriver driver) {
        super(driver);
    }

    public void fillInfoForJiraIntegration(Integration integration) {
        integrationPage
                .clickConfigureIntegrationButton()
                .enterJiraAddress(integration.getAddress())
                .clickJiraVersionButton()
                .enterJiraEmailAddress(integration.getEmail())
                .enterJiraApiToken(integration.getApiToken())
                .clickEnableJiraIntegrationButton();
    }
}
