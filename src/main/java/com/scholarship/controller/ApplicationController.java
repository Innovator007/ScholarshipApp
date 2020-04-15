package com.scholarship.controller;

import com.scholarship.model.Scholarship;
import com.scholarship.model.Application;
import com.scholarship.repository.ScholarshipRepository;
import com.scholarship.repository.ApplicationRepository;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ApplicationController {
   
	@Autowired
    ScholarshipRepository scholarshipRepository;

    @Autowired
    ApplicationRepository applicationRepository;


	@RequestMapping(value = "/scholarship/{id}/apply")
	public ModelAndView scholarshipIndex(@PathVariable String id, HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		Scholarship scholarships = scholarshipRepository.findById(id);
		return new ModelAndView("applyForm").addObject("scholarship", scholarship);
	}

	@RequestMapping(value = "/scholarship/{id}/apply", method = RequestMethod.POST)
	public ModelAndView scholarshipCreatePost(@PathVariable String id, @ModelAttribute Scholarship scholarship, HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		scholarshipRepository.save(scholarship);
		return new ModelAndView("redirect:/scholarships");
	}

}
