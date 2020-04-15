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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.scholarship.utils.CheckLoggedIn;

@Controller
public class AuthController {
   
	@Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PhilantropistRepository philantropistRepository;


	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView postLogin(@ModelAttribute Login loginpost, HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		String email = loginpost.getEmail();
		String password = loginpost.getPassword();
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user != null) {
			session.setAttribute("isLoggedIn", true);
			session.setAttribute("user", user);
			return new ModelAndView("redirect:/scholarships");
		} else {
			return new ModelAndView("redirect:/login");
		}
	}	

	@RequestMapping(value = "/signup")
	public ModelAndView signup(HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		return new ModelAndView("signupselect");
	}

	@RequestMapping(value = "/signup/philanthropist")
	public ModelAndView signupPhilantropist(HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		return new ModelAndView("philanthropistsignup");
	}

	@RequestMapping(value = "/signup/philanthropist", method = RequestMethod.POST)
	public ModelAndView postSignupPhilantropist(@ModelAttribute AllUserFields allUserFields, HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		String phoneNumber = allUserFields.getPhonenumber();
		String institute = allUserFields.getInstitute();
		String philantropistId = allUserFields.getPhilantropistid();
		String name = allUserFields.getName();
		String email = allUserFields.getEmail();
		String password = allUserFields.getPassword();
		String username = allUserFields.getUsername();
		int role = 1;
		User user = new User(name, username, email, password, role);
		Philantropist philanthropist = new Philantropist(phoneNumber, institute, philantropistId);
		user.setPhilanthropist(philanthropist);
		philanthropist.setUser(user);
		userRepository.save(user);

		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/signup/student")
	public ModelAndView signupStudent(HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		return new ModelAndView("studentsignup");
	}

	@RequestMapping(value = "/signup/student", method = RequestMethod.POST)
	public ModelAndView postSignupStudent(@ModelAttribute AllUserFieldsStudent allUserFieldsStudent, HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
		String phoneNumber = allUserFieldsStudent.getPhonenumber();
		String school = allUserFieldsStudent.getSchool();
		String studentId = allUserFieldsStudent.getStudentid();
		String name = allUserFieldsStudent.getName();
		String email = allUserFieldsStudent.getEmail();
		String password = allUserFieldsStudent.getPassword();
		String username = allUserFieldsStudent.getUsername();
		int role = 2;
		User user = new User(name, username, email, password, role);
		Student student = new Student(phoneNumber, school, studentId);
		user.setStudent(student);
		student.setUser(user);
		userRepository.save(user);

		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/signup/select", method = RequestMethod.POST)
	public ModelAndView postSignup(@ModelAttribute SignupMessage message, HttpSession session) {
		if(CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/scholarships");
		}
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
