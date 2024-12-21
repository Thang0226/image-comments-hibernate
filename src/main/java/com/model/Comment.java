package com.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int point;
    private String author;
    private String feedback;
    private int likeCount;

    public Comment() {
    }

    public Comment(int id, int point, String author, String feedback, int likeCount) {
        this.id = id;
        this.point = point;
        this.author = author;
        this.feedback = feedback;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeNumber) {
        this.likeCount = likeNumber;
    }
}
