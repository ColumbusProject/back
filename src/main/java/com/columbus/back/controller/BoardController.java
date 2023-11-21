package com.columbus.back.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;
import com.columbus.back.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/columbus/api/board/itinerary/write")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
        @RequestBody @Valid PostBoardRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }
}
