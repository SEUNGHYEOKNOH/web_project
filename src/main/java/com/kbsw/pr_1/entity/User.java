package com.kbsw.pr_1.entity;

import com.kbsw.pr_1.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;


    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;  // 사용자가 작성한 게시물

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;  // 사용자가 작성한 댓글

    public void update(UserRequestDto dto) {
        if (dto.getEmail() != null) this.email = dto.getEmail();
        if (dto.getPassword() != null) this.password = dto.getPassword();
        if (dto.getNickname() != null) this.nickname = dto.getNickname();
    }
}
