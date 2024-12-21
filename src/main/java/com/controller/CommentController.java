package com.controller;

import com.service.CommentService;
import com.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
