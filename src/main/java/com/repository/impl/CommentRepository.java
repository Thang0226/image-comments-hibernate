package com.repository.impl;

import com.model.Comment;
import com.repository.ICommentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentRepository implements ICommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("from Comment", Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment findById(int id) {
        String hql = "from Comment c where c.id = " + id;
        TypedQuery<Comment> query = em.createQuery(hql, Comment.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() != 0) {
            em.merge(comment);
        } else {
            em.persist(comment);
        }
    }

    @Override
    public void remove(int id) {
        Comment comment = findById(id);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
