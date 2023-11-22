package com.columbus.back.dto.response.board;

import com.columbus.back.common.ResponseCode;
import com.columbus.back.common.ResponseMessage;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.entity.ImageEntity;
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
    
    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {

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
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
