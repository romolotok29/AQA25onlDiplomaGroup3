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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiGetTest extends BaseApiTest {

    @Description("API GET Тест на проверку имеющихся проектов")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET проверка имеющихся проектов", description = "API GET проверка имеющихся проектов")
    public void getAllProjectsTest() {
        String endpoint = "index.php?/api/v2/get_projects";

        given()
                .when()
                .get(endpoint)
                .then().log().body()
                .body("limit", is(250))
                .statusCode(HttpStatus.SC_OK);
    }

    @Description("API GET Тест на соответствие сущности User")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET соответствие сущности User", description = "API GET соответствие сущности User")
    public void getTestUserSimple() {
        int userID = 1;

        User expectedUser = User.builder()
                .name("Roland Molotok")
                .email("roland.from.laptop@gmail.com")
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

    @Description("API GET Тест_2 на соответствие сущности User")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "API GET соответствие сущности User ver.2",
            description = "API GET соответствие сущности User ver.2")
    public void getTestUserFull() {
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
        Assert.assertEquals(response.getBody().jsonPath().getString("name"), "Roland Molotok");
        Assert.assertEquals(response.getBody().jsonPath().getInt("id"), 1);
    }
}
