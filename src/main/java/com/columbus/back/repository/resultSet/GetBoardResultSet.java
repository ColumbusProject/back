package com.columbus.back.repository.resultSet;

public interface GetBoardResultSet {
    Integer getReviewNumber();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getUserId();
    String getWriterNickname();
    String getWriterProfileImage();

}
