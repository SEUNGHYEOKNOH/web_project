package com.kbsw.pr_1.repository;

import com.kbsw.pr_1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Additional query methods can be defined here if needed

    Optional<Movie> findByTitle(String title);

}
