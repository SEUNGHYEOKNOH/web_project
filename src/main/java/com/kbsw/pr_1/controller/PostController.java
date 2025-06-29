package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.PostRequestDto;
import com.kbsw.pr_1.dto.PostResponseDto;
import com.kbsw.pr_1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private  final PostService postservice;

    @PostMapping
    public ResponseEntity<PostResponseDto> create(@RequestBody PostRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(postservice.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAll(){
        return ResponseEntity.ok(postservice.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long Id){
        return ResponseEntity.ok(postservice.findById(Id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id, @RequestBody PostRequestDto request){
        return ResponseEntity.ok(postservice.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        postservice.delete(id);
        return ResponseEntity.noContent().build();
    }



}
