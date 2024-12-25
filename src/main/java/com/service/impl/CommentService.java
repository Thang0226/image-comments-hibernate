package com.service.impl;

import com.exception.BadCommentException;
import com.model.Comment;
import com.repository.ICommentRepository;
import com.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    private final String[] badWords = {"fat", "shit", "bad", "worst"};

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) throws BadCommentException {
        if (badComment(comment)) {
            throw new BadCommentException();
        }
        commentRepository.save(comment);
    }

    @Override
    public void remove(int id) {
        commentRepository.deleteById(id);
    }

    private boolean badComment(Comment comment) {
        String feedback = comment.getFeedback();
        boolean badComment = false;
        for (String badWord : badWords) {
            if (feedback.contains(badWord)) {
                badComment = true;
            }
        }
        return badComment;
    }
}
