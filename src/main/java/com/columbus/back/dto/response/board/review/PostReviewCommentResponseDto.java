package com.columbus.back.dto.response.board.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.response.ResponseCode;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostReviewCommentResponseDto extends ResponseDto {

    private PostReviewCommentResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostReviewCommentResponseDto> success() {
        PostReviewCommentResponseDto result = new PostReviewCommentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistReviewBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
}
