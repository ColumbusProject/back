package com.columbus.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="travel_review_image")
@Table(name="travel_review_image")
public class ImageEntity {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sequence;
    private int boardNumber;
    private String image;


    public ImageEntity(int boardNumber, String image) {
        this.boardNumber = boardNumber;
        this.image = image;
    }
}