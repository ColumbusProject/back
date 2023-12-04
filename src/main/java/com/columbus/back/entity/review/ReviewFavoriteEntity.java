package com.columbus.back.entity.review;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.columbus.back.entity.review.primaryKey.ReviewFavoritePk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "travel_review_favorite")
@Table(name = "travel_review_favorite")
@IdClass(ReviewFavoritePk.class)
public class ReviewFavoriteEntity {
    @Id
    private String userId;
    @Id
    private int reviewNumber;
}
