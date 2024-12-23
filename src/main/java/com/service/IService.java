package com.service;

import com.model.Comment;

import java.util.List;
import java.util.Optional;

public interface IService <T> {
    Iterable<T> findAll();

    void save(T comment);

    Optional<T> findById(int id);

    void remove(int id);
}
