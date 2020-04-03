package com.scholarship.controller;

import com.scholarship.model.Message;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    private static final String MESSAGE = "Hello, World!";

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Message hello() {
        Message message = new Message();
        message.setMessage(MESSAGE);
        return message;
    }

}