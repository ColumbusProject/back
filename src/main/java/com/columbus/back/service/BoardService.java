package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.response.board.GetBoardResponseDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
