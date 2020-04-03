package com.scholarship.controller;

import com.scholarship.model.User;
import com.scholarship.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class AuthController {

	@Autowired
    UserRepository userRepository;

    @PostMapping("/auth/signup")
    public User signup(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String username = body.get("username");
        String email = body.get("email");
        String password = body.get("password");
        return userRepository.save(new User(name, username, email, password));
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody Map<String, String> body) {
    	String username = body.get("username");
        String password = body.get("password");
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return "Login Successfull";
        } else {
            return "Invalid Credentials or user doesnot exist";
        }
    }

}
