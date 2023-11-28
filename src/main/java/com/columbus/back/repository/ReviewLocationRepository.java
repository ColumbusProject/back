package com.columbus.back.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.columbus.back.entity.ReviewLocationEntity;

public interface ReviewLocationRepository extends JpaRepository<ReviewLocationEntity, Integer> {
    
    ReviewLocationEntity findByReviewNumber(Integer reviewNumber);

    @Transactional
    void deleteByReviewNumber(Integer reviewNumber);
}
