package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping({"/"})
    public String welcome() {
        return "Hello, and welcome to the trivia center! Please navigate " +
                "to \"localhost:8080/instructions\" to begin.";
    }

    @GetMapping({"/instructions"})
    public String instructions() {
        return "Start by adding trivia questions. The required fields are "
        + "question, answer. Each value will automatically be assigned a question number,"
                + "ranging from 0 to the (number of questions - 1).";
    }
}
