package tests.api;

import baseEntities.BaseApiTest;
import com.google.gson.Gson;
import configuration.ReadProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

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
                .post(Endpoints.ADD_PROJECT)
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Description("API Post Тест на проверку создания проекта c использованием Json")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API Post создание проекта c использованием Json",
            description = "API Post создание проекта c использованием Json")
    public void addProjectUsingJsonFileTest() {

        given()
                .body(ReadProperties.class.getClassLoader().getResourceAsStream("dataForApiTest.json"))
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Description("API Post Тест на проверку обновления проекта")
    @Test(testName = "API Post обновление проекта", description = "API Post обновление проекта")
    public void updateProjectAPITest() {

        Project updatedProject = new Project();
        updatedProject.setName("New Project");
        updatedProject.setAnnouncement("Announcement");
        updatedProject.setAnnouncementShown(true);
        updatedProject.setProjectType(2);

        Gson gson = new Gson();
        String json = gson.toJson(updatedProject);

        Response response = given()
                .pathParam("project_id", 1)
                .body(json)
                .when()
                .post(Endpoints.UPDATE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        Assert.assertEquals(response.getBody().jsonPath().getString("announcement"), "Announcement");
        Assert.assertTrue(response.getBody().jsonPath().getBoolean("show_announcement"));
        //Assert.assertEquals(response.getBody().jsonPath().getInt("suite_mode"), 2);

    }

    @Description("Api Post Test на проверку удаления проекта")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Api Post удаление проекта")
    public void deleteProjectApiTest() {

        given()
                .pathParam("project_id", 3)
                .when()
                .post(Endpoints.DELETE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

    }
}