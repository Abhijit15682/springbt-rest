package com.spring.boot.rest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    // Endpoint: http://localhost:8080/api/hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    // Endpoint: http://localhost:8080/api/greet?name=John
    @GetMapping("/greet")
    public String greetUser(@RequestParam(defaultValue = "Guest") String name) {
        return "Welcome, " + name + "!";
    }
}
