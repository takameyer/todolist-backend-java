package com.takameyer.TodoListBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<html>" +
                "<body>" +
                "<h1>Welcome to my TodoList API</h1>" +
                "<p>This is a simple API build with Spring Boot and MongoDB.</p>" +
                "</body>" +
                "</html>";
    }
}
