package com.columbus.back.controller;

import javax.validation.Valid;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columbus.back.dto.request.board.PostBoardRequestDto;
import com.columbus.back.dto.request.board.PostCommentRequestDto;
import com.columbus.back.dto.response.board.GetBoardResponseDto;
import com.columbus.back.dto.response.board.GetFavoriteListResponseDto;
import com.columbus.back.dto.response.board.PostBoardResponseDto;
import com.columbus.back.dto.response.board.PostCommentResponseDto;
import com.columbus.back.dto.response.board.PutFavoriteResponseDto;
import com.columbus.back.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("columbus/api/board/review/write")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity <? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity <? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardNumber);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
        @RequestBody @Valid PostBoardRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, userId);
        return response;
    }

    @PostMapping("/{boardNumnber}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
        @RequestBody @Valid PostCommentRequestDto requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardNumber, userId);
        return response; 
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, userId);
        return response;
    }
}
