package com.columbus.back.dto.response.board.review;

import com.columbus.back.dto.response.ResponseCode;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.ResponseMessage;
import com.columbus.back.entity.review.ReviewImageEntity;
import com.columbus.back.entity.review.ReviewLocationEntity;
import com.columbus.back.repository.review.resultSet.GetReviewBoardResultSet;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetReviewBoardResponseDto extends ResponseDto {

    private int boardNumber;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String userId;
    private String writerNickname;
    private String writerProfileImage;
    private String title;
    private String reviewLocation;
    
    private GetReviewBoardResponseDto(GetReviewBoardResultSet resultSet, List<ReviewImageEntity> reviewImageEntities, ReviewLocationEntity reviewLocationEntity) {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (ReviewImageEntity reviewImageEntity: reviewImageEntities) {
            String boardImage = reviewImageEntity.getImage();
            boardImageList.add(boardImage);
        }
        
        this.boardNumber = resultSet.getReviewNumber();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = resultSet.getWriteDatetime();
        this.userId = resultSet.getUserId();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();

        this.reviewLocation = reviewLocationEntity.getLocation();
    }

    public static ResponseEntity<GetReviewBoardResponseDto> success(GetReviewBoardResultSet resultSet, List<ReviewImageEntity> reviewImageEntities, ReviewLocationEntity reviewLocationEntity) {
        GetReviewBoardResponseDto result = new GetReviewBoardResponseDto(resultSet, reviewImageEntities, reviewLocationEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistReviewBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
