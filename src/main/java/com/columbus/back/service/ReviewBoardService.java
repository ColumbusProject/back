package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.board.review.PostReviewBoardRequestDto;
import com.columbus.back.dto.request.board.review.PostReviewCommentRequestDto;
import com.columbus.back.dto.response.board.review.GetReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewCommentListResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewFavoriteListResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewCommentResponseDto;
import com.columbus.back.dto.response.board.review.PutReviewFavoriteResponseDto;

public interface ReviewBoardService {
    ResponseEntity<? super GetReviewBoardResponseDto> getReviewBoard(Integer reviewNumber);
    ResponseEntity<? super GetReviewFavoriteListResponseDto> getReviewFavoriteList(Integer reviewNumber);
    ResponseEntity<? super GetReviewCommentListResponseDto> getReviewCommentList(Integer reviewNumber);

    ResponseEntity<? super PostReviewBoardResponseDto> postReviewBoard(PostReviewBoardRequestDto dto, String userId);
    ResponseEntity<? super PostReviewCommentResponseDto> postReviewComment(PostReviewCommentRequestDto dto, Integer reviewNumber, String userId);

    ResponseEntity<? super PutReviewFavoriteResponseDto> putReviewFavorite(Integer reviewNumber, String userId);
}
