package com.kbsw.pr_1.dto;

import com.kbsw.pr_1.entity.Movie;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequestDto {

    private String title;
    private String director;
    private int releaseYear;
    private int rate;
    private String genre;
    private String description;

    public Movie toEntity(){
        return Movie.builder()
                .title(this.title)
                .director(this.director)
                .releaseYear(this.releaseYear)
                .rate(this.rate)
                .genre(this.genre)
                .description(this.description)
                .build();
    }

}
