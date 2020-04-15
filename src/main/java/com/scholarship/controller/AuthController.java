package com.scholarship.controller;

import com.scholarship.model.SignupMessage;
import com.scholarship.model.AllUserFields;
import com.scholarship.model.AllUserFieldsStudent;
import com.scholarship.model.Login;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView postLogin(@ModelAttribute Login loginpost) {
		String studentId = loginpost.getStudentid();
		String philantropistId = loginpost.getPhilantropistid();
		String password = loginpost.getPassword();

		System.out.println("studentId:"+ studentId);
		System.out.println("philantropistId:"+ philantropistId);

		if(studentId != null){
			return new ModelAndView("redirect:/scholarships");
		}else {
			return new ModelAndView("redirect:/scholarship/create");
		}
	}	

	@RequestMapping(value = "/signup")
	public ModelAndView signup() {
		return new ModelAndView("signupselect");
	}

	@RequestMapping(value = "/signup/philanthropist")
	public ModelAndView signupPhilantropist() {
		return new ModelAndView("philanthropistsignup");
	}

	@RequestMapping(value = "/signup/philanthropist", method = RequestMethod.POST)
	public ModelAndView postSignupPhilantropist(@ModelAttribute AllUserFields allUserFields) {
		String phoneNumber = allUserFields.getPhonenumber();
		String institute = allUserFields.getInstitute();
		String philantropistId = allUserFields.getPhilantropistid();
		String name = allUserFields.getName();
		String email = allUserFields.getEmail();
		String password = allUserFields.getPassword();
		String username = allUserFields.getUsername();

		System.out.println("phoneNumber:"+ phoneNumber);
		System.out.println("philantropistId:"+ philantropistId);

		User user = new User(name, username, email, password);

		Philantropist philanthropist = new Philantropist(phoneNumber, institute, philantropistId);

		user.setPhilanthropist(philanthropist);

		philanthropist.setUser(user);

		userRepository.save(user);


		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/signup/student")
	public ModelAndView signupStudent() {
		return new ModelAndView("studentsignup");
	}

	@RequestMapping(value = "/signup/student", method = RequestMethod.POST)
	public ModelAndView postSignupStudent(@ModelAttribute AllUserFieldsStudent allUserFieldsStudent) {
		String phoneNumber = allUserFieldsStudent.getPhonenumber();
		String school = allUserFieldsStudent.getSchool();
		String studentId = allUserFieldsStudent.getStudentid();
		String name = allUserFieldsStudent.getName();
		String email = allUserFieldsStudent.getEmail();
		String password = allUserFieldsStudent.getPassword();
		String username = allUserFieldsStudent.getUsername();

		System.out.println("phoneNumber:"+ phoneNumber);
		System.out.println("studentId:"+ studentId);

		User user = new User(name, username, email, password);

		Student student = new Student(phoneNumber, school, studentId);

		user.setStudent(student);

		student.setUser(user);

		userRepository.save(user);


		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/signup/select", method = RequestMethod.POST)
	public ModelAndView postSignup(@ModelAttribute SignupMessage message) {
		String role = message.getRole();
		String redirectUrl = "";

      	if(role.equals("philanthropist")){
      		redirectUrl = "/signup/philanthropist";
      	} else if (role.equals("student")) {
      		redirectUrl = "/signup/student";
      	}

      	return new ModelAndView("redirect:"+redirectUrl);
	}
}
