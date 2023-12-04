package com.columbus.back.dto.response.board.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.columbus.back.common.CommentListItem;
import com.columbus.back.dto.response.ResponseCode;
import com.columbus.back.dto.response.ResponseDto;
import com.columbus.back.dto.response.ResponseMessage;
import com.columbus.back.repository.review.resultSet.GetReviewCommentListResultSet;

import lombok.Getter;

@Getter
public class GetReviewCommentListResponseDto extends ResponseDto {
    
    private List<CommentListItem> commentList;

    private GetReviewCommentListResponseDto(List<GetReviewCommentListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetReviewCommentListResponseDto> success(List<GetReviewCommentListResultSet> resultSets) {
        GetReviewCommentListResponseDto result = new GetReviewCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistReviewBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
