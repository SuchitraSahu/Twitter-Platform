package com.h2.oop.twitterplatform.domain;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")

public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;
    private String postBody;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post()
    {

    }

    public Post(String postBody, User user)
    {
        this.postBody = postBody;
        this.date = new Date();
        this.user = user;

    }


    public int getPostID()
    {
        return postID;
    }
    public void setPostBody(String postBody)
    {
        this.postBody = postBody;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    public String getPostBody()
    {
        return postBody;
    }

    public User getUser()
    {
        return user;
    }

    public Date getDate()
    {
        return date;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}