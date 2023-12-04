package com.columbus.back.repository.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.columbus.back.entity.review.primaryKey.ReviewFavoritePk;
import com.columbus.back.repository.review.resultSet.GetReviewFavoriteListResultSet;
import com.columbus.back.entity.review.ReviewFavoriteEntity;

@Repository
public interface ReviewFavoriteRepository extends JpaRepository<ReviewFavoriteEntity, ReviewFavoritePk> {

    ReviewFavoriteEntity findByReviewNumberAndUserId(Integer ReviewNumber, String userId);

    @Query(
        value = 
        "SELECT " +
        "U.nickname AS nickname, " +
        "U.profile_image AS profile_image " +
        "FROM travel_review_favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.user_id = U.user_id " +
        "WHERE F.review_number = ?1",
        nativeQuery = true
    )
    List<GetReviewFavoriteListResultSet> getReviewFavoriteList(Integer ReviewNumber); 
}
