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
import static org.hamcrest.Matchers.equalTo;

public class ApiPostTests extends BaseApiTest {

    @Description("Creating a Project entity API test")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create a project API test", description = "Create a project API test")
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

    @Description("Creating a Project entity with all fields API test")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Creating a project with all fields",
            description = "Creating a project with all fields")
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

    @Description("Creating a Project entity using Json file test")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Creating a project using Json file",
            description = "Creating a project using Json file")
    public void addProjectUsingJsonFileTest() {

        Response response = given()
                .body(ReadProperties.class.getClassLoader().getResourceAsStream("dataForApiTest.json"))
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        Assert.assertEquals(response.getBody().jsonPath().getInt("suite_mode"), 2);
        Assert.assertEquals(response.getBody().jsonPath().getString("announcement"), "Announcement 2");
        Assert.assertTrue(response.getBody().jsonPath().getBoolean("show_announcement"));

    }

    @Description("Update a Project entity API test")
    @Test(testName = "Update a project API test", description = "Update a project API test")
    public void updateProjectAPITest() {

        Project updatedProject = new Project();
        updatedProject.setName("New Project");
        updatedProject.setAnnouncement("New Announcement");
        updatedProject.setAnnouncementShown(true);

        Gson gson = new Gson();
        String json = gson.toJson(updatedProject);

        given()
                .pathParam("project_id", 1)
                .body(json)
                .when()
                .post(Endpoints.UPDATE_PROJECT)
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("announcement", equalTo("New Announcement"))
                .body("show_announcement", equalTo(true));
    }

    @Description("Delete a Project entity API test")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Delete a project API test")
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