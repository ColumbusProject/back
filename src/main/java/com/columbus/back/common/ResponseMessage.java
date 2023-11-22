package com.columbus.back.common;

public interface ResponseMessage {
  
    // HTTP status 200
    String SUCCESS = "Success.";

    // HTTP status 400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_NICKNAME = "Duplicate nickname.";
    String DUPLICATE_EMAIL = "Duplicate email.";
    String DUPLICATE_TELEPHONE_NUMBER = "Duplicate tel number";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "This board does not exist.";
  
    // HTTP status 401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization Failed.";
  
    // HTTP status 403
    String NO_PERMISSION = "Do not have permission.";
  
    // HTTP status 500
    String DATABASE_ERROR = "Database error.";
}
