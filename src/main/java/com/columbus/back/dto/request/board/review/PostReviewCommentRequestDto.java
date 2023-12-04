package com.columbus.back.dto.request.board.review;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewCommentRequestDto {

    @NotBlank
    private String content;
    
}
