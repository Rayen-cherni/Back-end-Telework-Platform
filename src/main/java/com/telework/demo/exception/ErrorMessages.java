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

    String PROJECT_NOT_FOUND = "project not found ";
    String PROJECT_ALREADY_EXISTS = "project already exist ";
    /**
     * Create pole page's error
     **/
    String POLE_MANAGER_ALREADY_IN_USE = "Pole manager already in use in another pole ";
    String POLE_MANAGER_NOT_FOUND = "pole manager not found ";



    String USER_NOT_FOUND_BY_ID = "Couldn't find your ID !";
    String USER_NOT_FOUND_BY_EMAIL = "Couldn't find your email !";
    String USER_OUT_OF_SERVICE = "User temporarily out of service ";

    String ADMIN_NOT_FOUND = "Admin not found ";

    String DEVELOPER_NOT_FOUND = "Developer not found ";
    String DEVELOPER_ALREADY_EXISTS = "Developer already exist ";
    String DEVELOPER_OUT_OF_SERVICE = "Developer temporarily out of service ";

    String HISTORIQUE_NOT_FOUND = "Histroqiue not found ";


    String POLE_NOT_FOUND = "pole not found ";
    String POLE_ALREADY_EXISTS = "Pole already exist ";

    String PROJECT_MANAGER_NOT_FOUND = "project manager not found";


    String ROLE_NOT_FOUND_BY_NAME = "role not found ";
    String ROLE_NOT_FOUND_BY_ID = "role not found with id: ";
    String ROLE_ALREADY_EXISTS = "role already exist ";

    String CHANGE_PASSWORD_ERROR = "The old password you have entered is incorrect";

}
