package com.service;

import com.model.Comment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CommentService implements IService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Comment> findAll() {
        String queryStr = "FROM Comment";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        return query.getResultList();
    }

    @Override
    public void add(Comment comment) {
        update(comment);
    }

    @Override
    public Comment findById(int id) {
        String queryStr = "FROM Comment WHERE id = :id";
        TypedQuery<Comment> query = entityManager.createQuery(queryStr, Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Comment comment) {
        Transaction transaction = null;
        Comment origin;
        if (comment.getId() == 0) {
            origin = new Comment();
        } else {
            origin = findById(comment.getId());
        }
        try (Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            origin.setAuthor(comment.getAuthor());
            origin.setPoint(comment.getPoint());
            origin.setFeedback(comment.getFeedback());
            origin.setLikeCount(comment.getLikeCount());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(int id) {
        Comment comment = findById(id);
        if (comment != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(comment);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
