package com.repository;

import com.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ICommentRepository extends CrudRepository<Comment, Integer> {
}
