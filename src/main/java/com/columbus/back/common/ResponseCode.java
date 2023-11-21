package com.columbus.back.common;

public interface ResponseCode {
  
  // HTTP status 200
  String SUCCESS = "SU";

  // HTTP status 400
  String VALIDATION_FAILED = "VF";
  String DUPLICATE_NICKNAME = "DN";
  String DUPLICATE_EMAIL = "DE";
  String DUPLICATE_TELEPHONE_NUMBER = "DT";
  String NOT_EXISTED_USER = "NU";
  String NOT_EXISTED_BOARD = "NB";

  // HTTP status 401
  String SIGN_IN_FAIL = "SF";
  String AUTHORIZATION_FAIL = "AF";

  // HTTP status 403
  String NO_PERMISSION = "NP";

  // HTTP status 500
  String DATABASE_ERROR = "DBE";
}
