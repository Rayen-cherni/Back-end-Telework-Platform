package com.telework.demo.exception;

public interface ErrorMessages {

    /**
     * Login page's errors
     **/
    String USER_NOT_FOUND = "These credentials do not match our records !";

    String CREDENTIALS_NOT_VALID = "Password not valid !";

    /**
     * Create Account page's error
     **/
    String USER_ALREADY_EXISTS = "User already exist";


    /**
     * Create project page's error
     **/
    String PROJECTS_ALREADY_EXISTS = "Projects already exist";


    /**
     * Development environment errors
     **/
    String ADMIN_NOT_FOUND = "Admin not found with id: ";
    String ADMIN_ALREADY_EXISTS = "Admin already exist";

    String DEVELOPER_NOT_FOUND = "Developer not found with id:";
    String DEVELOPER_ALREADY_EXISTS = "Developer already exist";

    String HISTORIQUE_NOT_FOUND = "Histroqiue not found with id:";

    String POLE_MANAGER_NOT_FOUND = "pole manager not found with id:";
    String POLE_MANAGER_ALREADY_EXISTS = "Pole manager already exist";

    String POLE_NOT_FOUND = "pole not found with id:";
    String POLE_ALREADY_EXISTS = "Pole already exist";

    String PROJECT_MANAGER_NOT_FOUND = "project manager not found with id:";
    String PROJECT_MANAGER_ALREADY_EXISTS = "project manager already exist";

    String PROJECT_NOT_FOUND = "project not found with id:";

    String ROLE_NOT_FOUND = "role not found with id:";
    String ROLE_ALREADY_EXISTS = "role already exist";


}
