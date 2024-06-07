package baseEntities;

import configuration.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;
import services.ProjectService;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    protected ProjectService projectService;

    @BeforeTest
    public void setupApi() {
        // Setup RestAssured
        RestAssured.baseURI = ReadProperties.getUrl();

        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.email(), ReadProperties.password())
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);

        projectService = new ProjectService();
    }
}
