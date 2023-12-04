package com.columbus.back.controller.board;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columbus.back.dto.request.board.review.PostReviewBoardRequestDto;
import com.columbus.back.dto.request.board.review.PostReviewCommentRequestDto;
import com.columbus.back.dto.response.board.review.GetReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewCommentListResponseDto;
import com.columbus.back.dto.response.board.review.GetReviewFavoriteListResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewBoardResponseDto;
import com.columbus.back.dto.response.board.review.PostReviewCommentResponseDto;
import com.columbus.back.dto.response.board.review.PutReviewFavoriteResponseDto;
import com.columbus.back.service.ReviewBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("columbus/api/board/review")
@RequiredArgsConstructor
public class ReviewBoardController {
    
    private final ReviewBoardService reviewBoardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity <? super GetReviewBoardResponseDto> getReviewBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity <? super GetReviewBoardResponseDto> response = reviewBoardService.getReviewBoard(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetReviewFavoriteListResponseDto> getFavoriteList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetReviewFavoriteListResponseDto> response = reviewBoardService.getReviewFavoriteList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetReviewCommentListResponseDto> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetReviewCommentListResponseDto> response = reviewBoardService.getReviewCommentList(boardNumber);
        return response;
    }

    @PostMapping("/write")
    public ResponseEntity<? super PostReviewBoardResponseDto> postBoard(
        @RequestBody @Valid PostReviewBoardRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostReviewBoardResponseDto> response = reviewBoardService.postReviewBoard(requestBody, userId);
        return response;
    }

    @PostMapping("/{boardNumnber}/comment")
    public ResponseEntity<? super PostReviewCommentResponseDto> postComment(
        @RequestBody @Valid PostReviewCommentRequestDto requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PostReviewCommentResponseDto> response = reviewBoardService.postReviewComment(requestBody, boardNumber, userId);
        return response; 
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutReviewFavoriteResponseDto> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PutReviewFavoriteResponseDto> response = reviewBoardService.putReviewFavorite(boardNumber, userId);
        return response;
    }
}
