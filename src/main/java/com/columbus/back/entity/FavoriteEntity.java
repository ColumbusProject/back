package com.columbus.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.columbus.back.entity.primaryKey.FavoritePk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "travel_review_favorite")
@Table(name = "travel_review_favorite")
@IdClass(FavoritePk.class)
public class FavoriteEntity {
    @Id
    private String userId;
    @Id
    private int reviewNumber;
}
