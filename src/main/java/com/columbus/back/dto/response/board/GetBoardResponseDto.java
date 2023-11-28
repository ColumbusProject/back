package com.columbus.back.dto.response.board;

import com.columbus.back.dto.response.ResponseCode;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.ResponseMessage;
import com.columbus.back.entity.ImageEntity;
import com.columbus.back.entity.ReviewLocationEntity;
import com.columbus.back.repository.resultSet.GetBoardResultSet;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private int boardNumber;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String userId;
    private String writerNickname;
    private String writerProfileImage;
    private String title;
    private String reviewLocation;
    
    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities, ReviewLocationEntity reviewLocationEntity) {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity: imageEntities) {
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardNumber = resultSet.getBoardNumber();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = resultSet.getWriteDatetime();
        this.userId = resultSet.getUserId();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();

        this.reviewLocation = reviewLocationEntity.getReviewLocation();
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities, ReviewLocationEntity reviewLocationEntity) {
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities, reviewLocationEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
