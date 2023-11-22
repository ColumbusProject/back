package com.columbus.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.columbus.back.entity.BoardEntity;

public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {
    
    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber(Integer boardNumber);
    
}
