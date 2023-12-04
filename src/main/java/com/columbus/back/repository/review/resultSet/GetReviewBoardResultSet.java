package com.columbus.back.repository.review.resultSet;

public interface GetReviewBoardResultSet {
    Integer getReviewNumber();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getUserId();
    String getWriterNickname();
    String getWriterProfileImage();

}
