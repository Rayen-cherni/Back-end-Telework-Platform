package com.telework.demo.exception;

public enum ErrorMessages {

    /**
     * Login page's errors
     **/
    USER_NOT_FOUND("These credentials do not match our records !"),
    CREDENTIALS_NOT_VALID("Password not valid !"),

    /**
     * Create Account page's error
     **/
    USER_ALREADY_EXISTS("User already exist"),


    /**
     * Create project page's error
     **/
    PROJECTS_ALREADY_EXISTS("Projects already exist"),

    /**
     * Development environment errors
     **/
    ADMIN_NOT_FOUND("Admin not found with id: "),
    ADMIN_ALREADY_EXISTS("Admin already exist"),

    DEVELOPER_NOT_FOUND("Developer not found with id:"),
    DEVELOPER_ALREADY_EXISTS("Developer already exist"),

    HISTORIQUE_NOT_FOUND("Histroqiue not found with id:"),

    POLE_MANAGER_NOT_FOUND("pole manager not found with id:"),
    POLE_MANAGER_ALREADY_EXISTS("Pole manager already exist"),

    POLE_NOT_FOUND("pole not found with id:"),
    POLE_ALREADY_EXISTS("Pole already exist"),
    PROJECT_MANAGER_NOT_FOUND("project manager not found with id:"),
    PROJECT_NOT_FOUND("project not found with id:"),
    ROLE_NOT_FOUND("role not found with id:");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
