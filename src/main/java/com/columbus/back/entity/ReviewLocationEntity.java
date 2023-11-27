package com.columbus.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.parsing.Location;

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
    private int boardNumber;
    private String reviewLocation;

    public ReviewLocationEntity(int boardNumber, String reviewLocation) {
        this.boardNumber = boardNumber;
        this.reviewLocation = reviewLocation;
    }
}
