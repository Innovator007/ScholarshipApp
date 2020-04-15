package com.scholarship.controller;

import com.scholarship.model.User;
import com.scholarship.model.Philantropist;
import com.scholarship.model.Scholarship;
import com.scholarship.repository.ScholarshipRepository;
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
public class PhilantropistController {

    @Autowired
    ScholarshipRepository scholarshipRepository;

	@RequestMapping(value = "/philantropist/scholarships")
	public ModelAndView scholarshipIndex(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		String role = "";
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 1) {
				Philantropist philantropist = user.getPhilantropist();
				List<Scholarship> scholarships = scholarshipRepository.findByPhilantropist(philantropist);
				return new ModelAndView("philantropistscholarship").addObject("scholarships", scholarships).addObject("role", "philantropist");
			} else {
				return new ModelAndView("redirect:/scholarships");
			}
		}
		return new ModelAndView("redirect:/login");
	}


}
