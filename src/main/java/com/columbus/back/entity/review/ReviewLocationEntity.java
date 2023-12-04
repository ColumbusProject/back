package com.columbus.back.entity.review;

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
@Entity(name = "travel_review_location")
@Table(name = "travel_review_location")
public class ReviewLocationEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private int reviewNumber;
    private String location;

    public ReviewLocationEntity(int reviewNumber, String reviewLocation) {
        this.reviewNumber = reviewNumber;
        this.location = reviewLocation;
    }
}
