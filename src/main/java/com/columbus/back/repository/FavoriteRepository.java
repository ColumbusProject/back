package com.columbus.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.columbus.back.entity.FavoriteEntity;
import com.columbus.back.entity.primaryKey.FavoritePk;
import com.columbus.back.repository.resultSet.GetFavoriteListResultSet;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardNumberAndUserId(Integer boardNumber, String userId);

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
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber); 
}
