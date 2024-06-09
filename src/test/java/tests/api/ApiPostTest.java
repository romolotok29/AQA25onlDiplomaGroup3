package tests.api;

import baseEntities.BaseApiTest;
import configuration.ReadProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiPostTest extends BaseApiTest {

    @Description("API Post Тест на проверку создания проекта")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API Post проверка создания проекта", description = "API Post проверка создания проекта")
    public void addProjectSimpleApiTest() {
        String endpoint = "index.php?/api/v2/add_project";

        Project expectedProject = new Project();
        expectedProject.setName("AQA25-onl_SimpleApi");
        expectedProject.setAnnouncement("Announcement");
        expectedProject.setAnnouncementShown(true);
        expectedProject.setProjectType(1);

        given()
                .body(String.format("{\n" +
                                "    \"name\": \"%s\",\n" +
                                "    \"announcement\": \"%s\",\n" +
                                "    \"show_announcement\": %b,\n" +
                                "    \"suite_mode\": %d\n" +
                                "}",
                        expectedProject.getName(),
                        expectedProject.getAnnouncement(),
                        expectedProject.isAnnouncementShown(),
                        expectedProject.getProjectType()))
                .when()
                .post(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Description("API Post Тест на проверку создания проекта cо всеми полями")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API Post создание проекта cо всеми полями",
            description = "API Post создание проекта cо всеми полями")
    public void addProjectFullApiTest() {
        String endpoint = "index.php?/api/v2/add_project";

        Project expectedProject = new Project();
        expectedProject.setName("AQA25-onl_FullApi");
        expectedProject.setAnnouncement("Announcement");
        expectedProject.setAnnouncementShown(true);
        expectedProject.setProjectType(2);
        expectedProject.setApprovalEnabled(true);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", expectedProject.getName());
        jsonAsMap.put("announcement", expectedProject.getAnnouncement());
        jsonAsMap.put("show_announcement", expectedProject.isAnnouncementShown());
        jsonAsMap.put("suite_mode", expectedProject.getProjectType());
        jsonAsMap.put("case_statuses_enabled", expectedProject.isApprovalEnabled());

        given()
                .body(jsonAsMap)
                .when()
                .post(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Description("API Post Тест на проверку создания проекта c использованием Json")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API Post создание проекта c использованием Json",
            description = "API Post создание проекта c использованием Json")
    public void addProjectUsingJsonFileTest() {
        String endpoint = "index.php?/api/v2/add_project";

        given()
                .body(ReadProperties.class.getClassLoader().getResourceAsStream("dataForApiTest.json"))
                .log().body()
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
