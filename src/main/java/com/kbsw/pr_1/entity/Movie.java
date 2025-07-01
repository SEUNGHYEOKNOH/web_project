package com.kbsw.pr_1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String director;
    private int releaseYear;
    private int rate;
    private String genre;
    private String description;

    public Movie(String title, String director, int releaseYear, int rate, String genre, String description) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rate = rate;
        this.genre = genre;
        this.description = description;
    }

    public void update(String title, String director, int releaseYear, int rate, String genre, String description) {
        if (title != null) this.title = title;
        if (director != null) this.director = director;
        if (releaseYear > 0) this.releaseYear = releaseYear;
        if (rate >= 0 && rate <= 10) this.rate = rate; // Assuming rate is between 0 and 10
        if (genre != null) this.genre = genre;
        if (description != null) this.description = description;
    }



}
