package com.h2.oop.twitterplatform.controller;

import com.h2.oop.twitterplatform.domain.Comment;
import com.h2.oop.twitterplatform.domain.Post;
import com.h2.oop.twitterplatform.domain.User;
import com.h2.oop.twitterplatform.repository.CommentRepository;
import com.h2.oop.twitterplatform.repository.PostRepository;
import com.h2.oop.twitterplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController
{
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository)
    {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;

    }

//    @PostMapping
//    public ResponseEntity<?> addComment(@RequestBody CommentRequest request)
//    {
//        Optional<User> user = userRepository.findById(request.getUserID());
//
//        if (!user.isPresent())
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"User does not exist\"}"
//            );
//        }
//
//        Optional<Post> post = postRepository.findById(request.getPostID());
//        if (!post.isPresent())
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"Post does not exist\"}");
//        }
//
//        Comment comment = new Comment(request.getCommentBody(), post.get(), user.get());
//        commentRepository.save(comment);
//
//        return ResponseEntity.ok("Comment created successfully");
//    }
@PostMapping
public ResponseEntity<?> addComment(@RequestBody CommentRequest request) {

    Optional<Post> post = postRepository.findById(request.getPostID());
    if (!post.isPresent())
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"Post does not exist\"}"
        );

    }

    Optional<User> user = userRepository.findById(request.getUserID());

    if (!user.isPresent()) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"User does not exist\"}"
        );

    }

    Comment comment = new Comment(request.getCommentBody(), post.get(), user.get());

    commentRepository.save(comment);

    return ResponseEntity.ok("Comment created successfully");
}

    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam Long commentID)
    {
        Optional<Comment> optionalComment = commentRepository.findById(commentID);
        if (optionalComment.isPresent())
        {
            return ResponseEntity.ok(optionalComment.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"Comment does not exist\"}" );
        }
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody CommentRequest request)
    {
        Optional<Comment> optionalComment = commentRepository.findById(request.getCommentID());
        if (optionalComment.isPresent())
        {
            Comment comment = optionalComment.get();
            comment.setCommentBody(request.getCommentBody());
            commentRepository.save(comment);

            return ResponseEntity.ok("Comment edited successfully");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"Comment does not exist\"}");
        }

    }


    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam Long commentID)
    {
        if (commentRepository.existsById(commentID))
        {
            commentRepository.deleteById(commentID);

            return ResponseEntity.ok("Comment deleted");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\",: \"Comment does not exist\"}");
        }

    }

    static class CommentRequest
    {
        private Long commentID;
        private String commentBody;
        private Long userID;
        private Long postID;

        public Long getCommentID()
        {
            return commentID;
        }
        public void setCommentID(Long commentID)
        {
            this.commentID = commentID;
        }
        public String getCommentBody()
        {
            return commentBody;
        }
        public void setCommentBody(String commentBody)
        {
            this.commentBody = commentBody;
        }
        public Long getUserID()
        {
            return userID;
        }
        public void setUserID(Long userID)
        {
            this.userID = userID;
        }
        public Long getPostID()
        {
            return postID;
        }
        public void setPostID(Long postID)
        {
            this.postID = postID;
        }


    }
}




