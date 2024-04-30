package com.h2.oop.twitterplatform.controller;

import com.h2.oop.twitterplatform.domain.Post;
import com.h2.oop.twitterplatform.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.h2.oop.twitterplatform.repository.PostRepository;
import com.h2.oop.twitterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@RestController
@RequestMapping("/post")

public class PostController
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository)
    {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request)
    {
        Optional<User> optionalUser = userRepository.findById(request.getUserID());
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            Post post = new Post(request.getPostBody(), user);
            postRepository.save(post);
            return ResponseEntity.ok("Post created successfully");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", \"message\": \"User does not exist\"}");
        }
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam Long postID)
    {
        Optional<Post> optionalPost = postRepository.findById(postID);
        if (optionalPost.isPresent())
        {
            return ResponseEntity.ok(optionalPost.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }

    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody PostRequest request)
    {
        Optional<Post> optionalPost = postRepository.findById(request.getPostID());
        if (optionalPost.isPresent())
        {
            Post post = optionalPost.get();
            post.setPostBody(request.getPostBody());
            postRepository.save(post);

            return ResponseEntity.ok("Post edited successfully");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam Long postID)
    {
        if (postRepository.existsById(postID))
        {
            postRepository.deleteById(postID);

            return ResponseEntity.ok("Post deleted");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }


    static class PostRequest
    {
        private Long postID;
        private Long userID;
        private String postBody;

        public Long getPostID()
        {
            return postID;
        }

        public void setPostID(Long postID)
        {
            this.postID = postID;
        }

        public String getPostBody()
        {
            return postBody;
        }

        public void setPostBody(String postBody)
        {
            this.postBody = postBody;
        }

        public Long getUserID()
        {
            return userID;
        }

        public void setUserID(Long userId)
        {
            this.userID = userId;
        }


    }
}
