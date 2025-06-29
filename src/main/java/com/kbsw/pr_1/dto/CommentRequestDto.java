package com.kbsw.pr_1.dto;

import com.kbsw.pr_1.entity.Comment;
import com.kbsw.pr_1.entity.Post;
import com.kbsw.pr_1.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private String content;
    private Long userId;
    private Long postId;

    public Comment toEntity(User user , Post post){
        return Comment.builder()
                .content(this.content)
                .user(user)
                .post(post)
                .build();
    }
}
