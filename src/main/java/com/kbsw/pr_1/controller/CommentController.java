package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.CommentRequestDto;
import com.kbsw.pr_1.dto.CommentResponseDto;
import com.kbsw.pr_1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //create
    @PostMapping
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentRequestDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(request));
    }
    //findAll
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id, @RequestBody CommentRequestDto request){
        return ResponseEntity.ok(commentService.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
