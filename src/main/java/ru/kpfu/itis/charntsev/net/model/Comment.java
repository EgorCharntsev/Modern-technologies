package ru.kpfu.itis.charntsev.net.model;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int postId;
    private String comment;
    private Date date;

    public Comment(int id, int userId, int postId, String comment, Date date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
        this.date = date;
    }

    public Comment( int userId, int postId, String comment, Date date) {
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
