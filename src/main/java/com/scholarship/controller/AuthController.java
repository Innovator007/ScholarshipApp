package com.scholarship.controller;

import com.scholarship.model.SignupMessage;
import com.scholarship.model.Student;
import com.scholarship.model.Philantropist;
import com.scholarship.model.User;
import com.scholarship.repository.UserRepository;
import com.scholarship.repository.StudentRepository;
import com.scholarship.repository.PhilantropistRepository;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class AuthController {
   
	@Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PhilantropistRepository philantropistRepository;


	@RequestMapping(value = "/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/signup")
	public ModelAndView signup() {
		return new ModelAndView("signupselect");
	}

	@RequestMapping(value = "/signup/philanthropist")
	public ModelAndView signupPhilantropist() {
		return new ModelAndView("philanthropistsignup");
	}

	@RequestMapping(value = "/signup/student")
	public ModelAndView signupStudent() {
		return new ModelAndView("studentsignup");
	}

	@RequestMapping(value = "/signup/select", method = RequestMethod.POST)
	public ModelAndView postSignup(@ModelAttribute SignupMessage message) {
		String role = message.getRole();
		String redirectUrl = "";
      	if(role == "philanthropist") {
      		redirectUrl = "login";
      	} else if (role == "student") {
      		redirectUrl = "signup";
      	}
      	return new ModelAndView(redirectUrl);
	}

}
