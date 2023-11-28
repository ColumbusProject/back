package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.request.board.PostCommentRequestDto;
import com.columbus.back.dto.response.board.GetBoardResponseDto;
import com.columbus.back.dto.response.board.GetCommentListResponseDto;
import com.columbus.back.dto.response.board.GetFavoriteListResponseDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;
import com.columbus.back.dto.response.board.PostCommentResponseDto;
import com.columbus.back.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer reviewNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer reviewNumber);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer reviewNumber);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String userId);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer reviewNumber, String userId);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer reviewNumber, String userId);
}
