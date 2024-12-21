package com.service;

import com.model.Comment;

import java.util.List;

public interface IService {
    List<Comment> findAll();

    void add(Comment comment);

    Comment findById(int id);

    void update(Comment comment);

    void remove(int id);
}
