package com.columbus.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.columbus.back.entity.ReviewLocationEntity;

public interface ReviewLocationRepository extends JpaRepository<ReviewLocationRepository, Integer> {
    
    List<ReviewLocationEntity> findByBoardNumber(Integer boardNumber);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
