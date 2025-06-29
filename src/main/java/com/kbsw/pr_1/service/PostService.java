package com.kbsw.pr_1.service;

import com.kbsw.pr_1.dto.PostRequestDto;
import com.kbsw.pr_1.dto.PostResponseDto;
import com.kbsw.pr_1.entity.Post;
import com.kbsw.pr_1.entity.User;
import com.kbsw.pr_1.repository.PostRepository;
import com.kbsw.pr_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto create(PostRequestDto request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Post post = request.toEntity(user);
        return PostResponseDto.fromEntity(postRepository.save(post));

    }
    @Transactional(readOnly = true)
    public PostResponseDto findById(Long Id){
        Post post = postRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return PostResponseDto.fromEntity(post);
    }
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    public PostResponseDto update(Long Id,PostRequestDto requset){
        Post post = postRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.update(requset.getTitle(),requset.getContent());
        return PostResponseDto.fromEntity(postRepository.save(post));
    }
    public void delete(Long Id){
        postRepository.deleteById(Id);
    }

}
