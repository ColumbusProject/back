package com.columbus.back.repository.review;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.columbus.back.entity.review.ReviewImageEntity;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Integer> {
    
    List<ReviewImageEntity> findByReviewNumber(Integer reviewNumber);

    @Transactional
    void deleteByReviewNumber(Integer reviewNumber);
}
