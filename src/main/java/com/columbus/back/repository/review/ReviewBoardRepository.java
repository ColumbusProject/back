package com.columbus.back.repository.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.columbus.back.entity.review.ReviewBoardEntity;
import com.columbus.back.repository.review.resultSet.GetReviewBoardResultSet;

public interface ReviewBoardRepository extends JpaRepository <ReviewBoardEntity, Integer> {

    boolean existsByReviewNumber(Integer reviewNumber);

    ReviewBoardEntity findByReviewNumber(Integer reviewNumber);
    
    @Query(
        value = 
        "SELECT " +
        "RB.review_number AS review_number, " +
        "RB.writer_id AS writer_id, " +
        "RB.title AS title, " +
        "RB.content AS content, " +
        "RB.write_datetime AS write_datetime, " +
        "U.nickname AS nickname, " +
        "U.profile_image AS profile_image " +
        "FROM travel_review AS RB " +
        "INNER JOIN user AS U " +
        "ON RB.writer_id = U.user_id " +
        "WHERE review_number = ?1; ",
        nativeQuery = true
    )
    GetReviewBoardResultSet getReviewBoard(Integer reviewNumber);
    
}
