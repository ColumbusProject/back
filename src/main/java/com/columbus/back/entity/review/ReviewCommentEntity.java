package com.columbus.back.entity.review;

import java.util.Date;
import java.time.Instant;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.columbus.back.dto.request.board.review.PostReviewCommentRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "travel_review_comment")
@Table(name = "travel_review_comment")
public class ReviewCommentEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNumber;
    private String content;
    private String writeDatetime;
    private String writerId;
    private int reviewNumber;

    public ReviewCommentEntity(PostReviewCommentRequestDto dto, Integer reviewNumber, String userId) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = SimpleDateFormat.format(now);

        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.writerId = userId;
        this.reviewNumber = reviewNumber;
    }
}
