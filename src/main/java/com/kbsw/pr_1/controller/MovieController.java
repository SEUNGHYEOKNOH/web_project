package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.MovieRequestDto;
import com.kbsw.pr_1.dto.MovieResponseDto;
import com.kbsw.pr_1.repository.MovieRepository;
import com.kbsw.pr_1.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
@Tag(name = "Movie", description = "영화 API")
public class MovieController {

    private final MovieService movieService;

    // 영화 관련 API 메서드들을 여기에 추가할 수 있습니다.
    // 예: 영화 목록 조회, 영화 상세 조회, 영화 생성, 영화 수정, 영화 삭제 등

    // 예시 메서드
     @GetMapping
     @Operation(summary = "영화 목록 조회", description = "모든 영화를 조회합니다.")
     public ResponseEntity <List<MovieResponseDto>> findAllMovies() {
         return ResponseEntity.ok(movieService.findAll());
     }

     @PostMapping
     @Operation(summary = "영화 생성", description = "새로운 영화를 생성합니다.")
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto requestDto){
        return ResponseEntity.ok(movieService.createMovie(requestDto));
     }

     @GetMapping("/{id}")
        @Operation(summary = "영화 상세 조회", description = "ID로 영화를 조회합니다.")
    public ResponseEntity<MovieResponseDto> findMovieById(Long id) {
        return ResponseEntity.ok(movieService.findMovieById(id));
     }

     @GetMapping("/title")
        @Operation(summary = "영화 제목으로 조회", description = "제목으로 영화를 조회합니다.")
    public ResponseEntity<MovieResponseDto> findMovieByTitle(String title) {
        return ResponseEntity.ok(movieService.findMovieByTitle(title));
     }

     @PutMapping("/{id}")
        @Operation(summary = "영화 수정", description = "ID로 영화를 수정합니다.")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto requestDto) {
        return ResponseEntity.ok(movieService.updateMovie(id, requestDto));
     }

     @DeleteMapping("/{id}")
        @Operation(summary = "영화 삭제", description = "ID로 영화를 삭제합니다.")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
     }


}
