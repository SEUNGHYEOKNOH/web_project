package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.CommentRequestDto;
import com.kbsw.pr_1.dto.CommentResponseDto;
import com.kbsw.pr_1.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글 API")
public class CommentController {

    private final CommentService commentService;

    //create
    @PostMapping
    @Operation(summary = "댓글 생성", description = "새로운 댓글을 생성합니다.")
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(request));
    }
    //findAll
    @GetMapping
    @Operation(summary = "모든 댓글 조회", description = "모든 댓글을 조회합니다.")
    public ResponseEntity<List<CommentResponseDto>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "댓글 조회", description = "ID로 댓글을 조회합니다.")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "댓글 수정", description = "ID로 댓글을 수정합니다.")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id, @RequestBody CommentRequestDto request){
        return ResponseEntity.ok(commentService.update(id, request));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제", description = "ID로 댓글을 삭제합니다.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
