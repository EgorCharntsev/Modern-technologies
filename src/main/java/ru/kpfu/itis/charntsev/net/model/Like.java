package ru.kpfu.itis.charntsev.net.model;

import java.util.Date;

public class Like {
    private int id;
    private int userId;
    private int postId;
    private Date date;

    public Like(int id, int userId, int postId, Date date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
    }

    public Like( int userId, int postId, Date date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
