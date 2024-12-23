package com.controller;

import com.model.Comment;
import com.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/img-of-the-day")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping
    public String showComments(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "index";
    }

    @PostMapping("/add-comment")
    public String addComment(Comment comment, RedirectAttributes redirectAttributes) {
        commentService.save(comment);
        redirectAttributes.addFlashAttribute("message", "New comment added");
        return "redirect:/img-of-the-day";
    }

    @GetMapping("/{id}/like")
    public String likeComment(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            Comment cm = comment.get();
            int currentCount = cm.getLikeCount();
            cm.setLikeCount(currentCount + 1);
            commentService.save(cm);
        }

        redirectAttributes.addFlashAttribute("message", "Liked!");
        return "redirect:/img-of-the-day";
    }
}
