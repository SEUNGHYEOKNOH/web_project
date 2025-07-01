package com.kbsw.pr_1.dto;

import com.kbsw.pr_1.entity.Movie;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponseDto {

    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private int rate;
    private String genre;
    private String description;

    public static MovieResponseDto fromEntity(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .rate(movie.getRate())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .build();
    }


}
