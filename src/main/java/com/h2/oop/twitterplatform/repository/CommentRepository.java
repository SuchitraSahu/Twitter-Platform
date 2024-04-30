package com.h2.oop.twitterplatform.repository;

import com.h2.oop.twitterplatform.domain.Comment;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment, Long> {
}