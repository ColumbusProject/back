package com.columbus.back.entity.review;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.columbus.back.dto.request.board.review.PatchReviewBoardRequestDto;
import com.columbus.back.dto.request.board.review.PostReviewBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "travel_review")
@Table(name = "travel_review")
public class ReviewBoardEntity {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int reviewNumber;
    private String title;
    private String content;
    private String writeDatetime;
    private int viewCount;
    private int commentCount;
    private int favoriteCount;
    private String writerId;

    public ReviewBoardEntity(PostReviewBoardRequestDto dto, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.content = dto.getContents();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
        this.commentCount = 0;
        this.favoriteCount = 0;
        this.writerId = userId;
        
    }

    public void patch(PatchReviewBoardRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }

    public void decreaseFavoriteCount() {
        this.favoriteCount--;
    }

}

