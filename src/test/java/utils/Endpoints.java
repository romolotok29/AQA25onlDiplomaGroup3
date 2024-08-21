package utils;

public class Endpoints {
    public static final String GET_USER = "/index.php?/api/v2/get_user/{user_id}";
    public static final String GET_USER_BY_EMAIL = "/index.php?/api/v2/get_user_by_email&email={email}";
    public static final String GET_USERS = "/index.php?/api/v2/get_users";
    public static final String GET_USERS_BY_PROJECT_ID = "index.php?/api/v2/get_users/{project_id}";
    public static final String GET_PROJECT = "/index.php?/api/v2/get_project/{project_id}";
    public static final String ADD_PROJECT = "/index.php?/api/v2/add_project";

}