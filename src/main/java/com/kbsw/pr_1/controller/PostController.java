package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.PostRequestDto;
import com.kbsw.pr_1.dto.PostResponseDto;
import com.kbsw.pr_1.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "Post", description = "게시글 API")
public class PostController {
    private  final PostService postservice;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
    public ResponseEntity<PostResponseDto> create(@RequestBody PostRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(postservice.create(request));
    }

    @GetMapping
    @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 조회합니다.")
    public ResponseEntity<List<PostResponseDto>> findAll(){
        return ResponseEntity.ok(postservice.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "게시글 조회", description = "ID로 게시글을 조회합니다.")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long Id){
        return ResponseEntity.ok(postservice.findById(Id));
    }
    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "ID로 게시글을 수정합니다.")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id, @RequestBody PostRequestDto request){
        return ResponseEntity.ok(postservice.update(id,request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "ID로 게시글을 삭제합니다.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        postservice.delete(id);
        return ResponseEntity.noContent().build();
    }



}
