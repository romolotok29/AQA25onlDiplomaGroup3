package services;

import models.Project;
import org.apache.http.HttpStatus;

public interface IProjectService {
    Project getProject(int id);
    Project addProject(Project project);
    Project updateProject(int id, Project project);
    HttpStatus deleteProject(int id);

}