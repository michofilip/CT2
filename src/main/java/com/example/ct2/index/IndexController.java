package com.example.ct2.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello Coderslab");
        return "index";
    }
}
