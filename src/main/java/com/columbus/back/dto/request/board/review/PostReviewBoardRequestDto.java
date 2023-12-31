package com.columbus.back.dto.request.board.review;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewBoardRequestDto {
    
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotNull
    private List<String> boardImageList;
    @NotBlank
    private String location;

}
