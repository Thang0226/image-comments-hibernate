package com.logger;

import com.model.Comment;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.service.impl.CommentService.save(..))", throwing = "e")
    public void logMethod(JoinPoint joinPoint, Exception e) {
        Object[] args = joinPoint.getArgs();
        Comment comment = (Comment) args[0];
        System.out.println("Bad word detected in comment: ");
        System.out.println("\tAuthor: " + comment.getAuthor());
        System.out.println("\tTime: " + comment.getTime());
        System.out.println("\tFeedback: " + comment.getFeedback());
    }
}
