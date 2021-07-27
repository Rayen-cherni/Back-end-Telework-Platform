package com.telework.demo.exception;

public interface ErrorMessages {

    /**
     * Login page's errors
     **/
    String AUTHENTICATION_USER_NOT_VALID = "These credentials do not match our records !";

    /**
     * Create Account page's error
     **/
    String USER_ALREADY_EXISTS = "User already exist ";
    String REGISTER_PROCESS_NOT_VALID = "There is an account with that email address";

    /**
     * Create project page's error
     **/

    String PROJECT_NOT_FOUND = "Project not found ";
    String PROJECT_ALREADY_EXISTS = "Project already exist ";
    String PROJECT_MANAGER_ALREADY_IN_USE = "Project manager already in use ";

    /**
     * Create pole page's error
     **/
    String POLE_MANAGER_ALREADY_IN_USE = "Pole manager already in use in another pole ";
    String POLE_MANAGER_NOT_FOUND = "Pole manager not found ";



    String USER_NOT_FOUND_BY_ID = "Couldn't find your ID !";
    String USER_NOT_FOUND_BY_EMAIL = "Couldn't find your email !";
    String USER_OUT_OF_SERVICE = "User temporarily out of service ";

    String ADMIN_NOT_FOUND = "Admin not found ";

    String DEVELOPER_NOT_FOUND = "Developer not found ";
    String DEVELOPER_ALREADY_EXISTS = "Developer already exist ";
    String DEVELOPER_OUT_OF_SERVICE = "Developer temporarily out of service ";
    String DEVELOPER_ALREADY_IN_USE = "Developer already in use ";


    String HISTORIQUE_NOT_FOUND = "Histroqiue not found ";


    String POLE_NOT_FOUND = "Pole not found ";
    String POLE_ALREADY_EXISTS = "Pole already exist ";
    String POLE_ALREADY_IN_USE = "Pole already in use ";

    String PROJECT_MANAGER_NOT_FOUND = "Project manager not found";


    String ROLE_NOT_FOUND_BY_NAME = "Role not found ";
    String ROLE_NOT_FOUND_BY_ID = "Role not found with id: ";
    String ROLE_ALREADY_EXISTS = "Role already exist ";

    String CHANGE_PASSWORD_ERROR = "The old password you have entered is incorrect";

}
