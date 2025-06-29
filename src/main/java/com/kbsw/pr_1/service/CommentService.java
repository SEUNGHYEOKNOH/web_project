package com.kbsw.pr_1.service;

import com.kbsw.pr_1.dto.CommentRequestDto;
import com.kbsw.pr_1.dto.CommentResponseDto;
import com.kbsw.pr_1.entity.Comment;
import com.kbsw.pr_1.entity.Post;
import com.kbsw.pr_1.entity.User;
import com.kbsw.pr_1.repository.CommentRepository;
import com.kbsw.pr_1.repository.PostRepository;
import com.kbsw.pr_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository comemntRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentResponseDto create(@RequestBody CommentRequestDto request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("작성자가 존재하지 않습니다."));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = request.toEntity(user, post);
        return CommentResponseDto.fromEntity(comemntRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(){
        return comemntRepository.findAll().stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long Id){
        Comment comment = comemntRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        return CommentResponseDto.fromEntity(comment);
    }

    public CommentResponseDto update(Long Id , CommentRequestDto request) {
        Comment comment = comemntRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.update(request.getContent());
        return CommentResponseDto.fromEntity(comemntRepository.save(comment));
    }

    public void delete(Long Id) {
        comemntRepository.deleteById(Id);
    }
}
