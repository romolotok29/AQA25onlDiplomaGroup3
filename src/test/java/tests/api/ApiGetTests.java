package tests.api;

import baseEntities.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiGetTests extends BaseApiTest {

    @Description("API GET Тест на проверку имеющихся проектов")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET проверка имеющихся проектов", description = "API GET проверка имеющихся проектов")
    public void getAllProjectsTest() {
        String endpoint = "index.php?/api/v2/get_projects";

        Response response = given()
                .when()
                .get(Endpoints.GET_PROJECTS)
                .then().log().body()
                .body("limit", is(250))
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().getInt("size"), 3);
    }

    @Description("API GET Тест на проверку имеющихся пользователей")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET проверка имеющихся Users", description = "API GET проверка имеющихся пользователей")
    public void getAllUsersTest() {

        Response response = given()
                .when()
                .get(Endpoints.GET_USERS)
                .then().log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().getInt("size"), 2);
    }

    @Description("API GET Тест на проверку имеющихся пользователей по id проекта")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET проверка Users по id проекта", description = "API GET проверка Users по id проекта")
    public void getAllUsersByProjectIdTest() {
        int projectID = 60;

        Response response = given()
                .pathParam("project_id", projectID)
                .get(Endpoints.GET_USERS_BY_PROJECT_ID)
                .then().log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        List<User> users = response.jsonPath().getList("users", User.class);

        Assert.assertEquals(users.size(), 2);

    }

    @Description("API GET Тест на проверку несуществующего User")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET несуществующий пользователь", description = "API GET несуществующий пользователь")
    public void invalidUserTest() {
        String endpoint = "/index.php?/api/v2/get_user/3";

        Response response = given()
                .when()
                .get(endpoint)
                .then().log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .response();

        Assert.assertEquals(
                response.getBody().asString(), "{\"error\":\"Field :user is not a valid user.\"}");
    }

    @Description("API GET Тест на проверку несуществующего проекта")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET несуществующий проект", description = "API GET несуществующий проект")
    public void invalidProjectTest() {
        String endpoint = "/api/v2/project/548";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Description("API GET Тест на проверку несуществующего Milestone")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET несуществующий Milestone", description = "API GET несуществующий Milestone")
    public void invalidMilestoneTest() {
        String endpoint = "/api/v2/get_milestone/246";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Description("API GET Тест на соответствие сущности User")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET соответствие сущности User", description = "API GET соответствие сущности User")
    public void getUserSimpleTest() {
        int userID = 2;

        User expectedUser = User.builder()
                .name("Artem Krasnovski")
                .email("kras.tioma@gmail.com")
                .isActive(true)
                .roleId(1)
                .role("Lead")
                .build();

        User actualUser = given()
                .pathParam("user_id", userID)
                .get(Endpoints.GET_USER)
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(User.class, ObjectMapperType.GSON);

        Assert.assertTrue(expectedUser.equals(actualUser));
    }

    @Description("API GET Тест на проверку существования первого пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET соответствие сущности User ver.2",
            description = "API GET соответствие сущности User ver.2")
    public void getUserFullTest() {
        int userID = 1;

        Response response = given()
                .pathParam("user_id", userID)
                .get(Endpoints.GET_USER)
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        response.then().log().body();
        Assert.assertEquals(response.getBody().jsonPath().getString("name"), "Rolands Molotoks");
        Assert.assertEquals(response.getBody().jsonPath().getInt("id"), 1);
    }

    @Description("API GET Тест на проверку существования второго пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET User ver.3", description = "API GET User ver.3")
    public void getUserByEmailTest() {
        String email = "kras.tioma@gmail.com";

        Response response = given()
                .pathParam("email", email)
                .get(Endpoints.GET_USER_BY_EMAIL)
                .then()
                .log().body()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().getString("email"), "kras.tioma@gmail.com");
        Assert.assertEquals(response.getBody().jsonPath().getInt("id"), 2);
    }

}