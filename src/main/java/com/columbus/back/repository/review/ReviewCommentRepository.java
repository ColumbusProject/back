package com.columbus.back.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.columbus.back.entity.review.ReviewCommentEntity;
import com.columbus.back.repository.review.resultSet.GetReviewCommentListResultSet;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewCommentEntity, Integer> {
    
    @Query(
        value = 
        "SELECT " +
        "U.nickname AS nickname, " +
        "U.profile_image AS profile_image, " +
        "C.content AS content, " +
        "C.write_datetime AS write_datetime " +
        "FROM travel_review_comment AS C " +
        "INNER JOIN user AS U " +
        "ON C.writer_id = U.user_id " +
        "WHERE C.review_number = ?1 " +
        "ORDER BY write_datetime DESC; ",
        nativeQuery = true
    )
    List<GetReviewCommentListResultSet> getReviewCommentList(Integer reviewNumber);

    
}
