package com.scholarship.controller;

import com.scholarship.model.User;
import com.scholarship.model.Student;
import com.scholarship.model.Scholarship;
import com.scholarship.model.Application;
import com.scholarship.repository.ApplicationRepository;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.scholarship.utils.CheckLoggedIn;

@Controller
public class StudentController {

    @Autowired
    ApplicationRepository applicationRepository;

	@RequestMapping(value = "/student/applications")
	public ModelAndView scholarshipIndex(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 2) {
				Student student = user.getStudent();
				List<Application> applications = applicationRepository.findByStudent(student);
				return new ModelAndView("appliedscholarship").addObject("applications", applications);
			} else {
				return new ModelAndView("redirect:/philantropist/scholarships");
			}
		}
		return new ModelAndView("redirect:/login");
	}


}
