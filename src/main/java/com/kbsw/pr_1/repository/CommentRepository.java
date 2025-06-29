package com.kbsw.pr_1.repository;

import com.kbsw.pr_1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Additional query methods can be defined here if needed
}
