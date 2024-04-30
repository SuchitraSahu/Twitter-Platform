package com.h2.oop.twitterplatform.repository;


import com.h2.oop.twitterplatform.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface PostRepository extends JpaRepository<Post, Long> { List<Post> findAllByOrderByDateDesc();
}