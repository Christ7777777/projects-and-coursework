package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping({"/"})
    public String welcome() {
        return "Hello, and welcome to the Driveway Parking App!";
    }

    @GetMapping({"/{name}"})
    public String welcome(@PathVariable String name) {
        return "Hello, " + name + ", and welcome to the Driveway Parking App!";
    }
}
