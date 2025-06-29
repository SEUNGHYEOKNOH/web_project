package com.kbsw.pr_1.dto;

import com.kbsw.pr_1.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;
    private String writer;
    private Long postId;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writer(comment.getUser().getNickname())
                .postId(comment.getPost().getId())
                .build();
    }
}
