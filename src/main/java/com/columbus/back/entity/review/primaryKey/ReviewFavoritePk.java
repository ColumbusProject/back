package com.columbus.back.entity.review.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFavoritePk implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "review_number")
    private int reviewNumber;
}
