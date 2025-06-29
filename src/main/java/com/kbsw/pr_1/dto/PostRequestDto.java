package com.kbsw.pr_1.dto;

import com.kbsw.pr_1.entity.Post;
import com.kbsw.pr_1.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String title;
    private String content;
    private Long userId;

    public Post toEntity(User user) {
        return new Post(this.title, this.content, user); // builder 없이 명시적 생성자 사용
    }
}