package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.request.board.PostCommentRequestDto;
import com.columbus.back.dto.response.board.GetBoardResponseDto;
import com.columbus.back.dto.response.board.GetFavoriteListResponseDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;
import com.columbus.back.dto.response.board.PostCommentResponseDto;
import com.columbus.back.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String userId);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String userId);
}
