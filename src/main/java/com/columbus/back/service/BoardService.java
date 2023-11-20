package com.columbus.back.service;

import org.springframework.http.ResponseEntity;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
