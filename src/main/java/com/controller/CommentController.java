package com.controller;

import com.model.Comment;
import com.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/img-of-the-day")
public class CommentController {

    @Autowired
    private IService commentService;

    @GetMapping
    public String showComments(Model model) {
        model.addAttribute("comments", commentService.findAll());
        return "index";
    }

    @PostMapping("/add-comment")
    public String addComment(Comment comment, RedirectAttributes redirectAttributes) {
        commentService.add(comment);
        redirectAttributes.addFlashAttribute("message", "New comment added");
        return "redirect:/img-of-the-day";
    }
}
