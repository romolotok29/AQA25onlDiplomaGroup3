package services;

import io.restassured.mapper.ObjectMapperType;
import models.Project;
import org.apache.http.HttpStatus;
import utils.Endpoints;

import static io.restassured.RestAssured.given;

public class ProjectService implements IProjectService {
    @Override
    public Project getProject(int id) {
        return given()
                .pathParam("project_id", id)
                .log().body()
                .when()
                .get(Endpoints.GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Project.class);
    }

    @Override
    public Project addProject(Project project) {
        return given()
                .body(project, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project updateProject(int id, Project project) {
        return null;
    }

    @Override
    public HttpStatus deleteProject(int id) {
        return null;
    }

}