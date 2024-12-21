package com.service;

import com.model.Comment;

import java.util.List;

public interface IService <T> {
    List<T> findAll();

    void save(T comment);

    T findById(int id);

    void remove(int id);
}
