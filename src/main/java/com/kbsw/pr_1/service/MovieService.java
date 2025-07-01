package com.kbsw.pr_1.service;

import com.kbsw.pr_1.dto.MovieRequestDto;
import com.kbsw.pr_1.dto.MovieResponseDto;
import com.kbsw.pr_1.entity.Movie;
import com.kbsw.pr_1.repository.MovieRepository;
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
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieResponseDto createMovie(MovieRequestDto request) {
        Movie movie = request.toEntity(); // DTO를 Entity로 변환
        return MovieResponseDto.fromEntity(movieRepository.save(movie));
    }

    @Transactional(readOnly = true)
    public MovieResponseDto findMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        return MovieResponseDto.fromEntity(movie);
    }
    @Transactional(readOnly = true)
    public MovieResponseDto findMovieByTitle(String title){
        Movie movie = movieRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        return MovieResponseDto.fromEntity(movie);
    }
    @Transactional(readOnly = true)
    public List<MovieResponseDto> findAll(){
        return movieRepository.findAll().stream()
                .map(MovieResponseDto::fromEntity)
                .collect(Collectors.toList());
    }


    public MovieResponseDto updateMovie(Long id, MovieRequestDto requestDto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        movie.update(
                requestDto.getTitle(),
                requestDto.getDirector(),
                requestDto.getReleaseYear(),
                requestDto.getRate(),
                requestDto.getGenre(),
                requestDto.getDescription()
        );
        return MovieResponseDto.fromEntity(movieRepository.save(movie));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
