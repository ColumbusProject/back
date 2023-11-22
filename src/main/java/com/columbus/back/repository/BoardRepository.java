package com.columbus.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.columbus.back.entity.BoardEntity;
import com.columbus.back.repository.resultSet.GetBoardResultSet;

public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {

    BoardEntity findByBoardNumber(Integer boardNumber);
    
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
    GetBoardResultSet getBoard(Integer boardNumber);
    
}
