package com.scholarship.controller;

import com.scholarship.model.Scholarship;
import com.scholarship.repository.ScholarshipRepository;
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
public class ScholarshipController {
   
	@Autowired
    ScholarshipRepository scholarshipRepository;


	@RequestMapping(value = "/scholarships")
	public ModelAndView scholarshipIndex(HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		List<Scholarship> scholarships = scholarshipRepository.findAll();
		return new ModelAndView("scholarships").addObject("scholarships", scholarships);
	}

	@RequestMapping(value = "/scholarship/create")
	public ModelAndView scholarshipCreate(HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("createScholarshipForm");
	}

	@RequestMapping(value = "/scholarship/edit")
	public ModelAndView scholarshipEdit(HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("editScholarship");
	}

	@RequestMapping(value = "/scholarship/view/{id}")
	public ModelAndView scholarshipView(@PathVariable String id, HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		int scholarshipId = Integer.parseInt(id);
		Scholarship scholarship = scholarshipRepository.findById(scholarshipId);
		return new ModelAndView("scholarshipdetail").addObject("scholarship", scholarship);
	}

	@RequestMapping(value = "/scholarship/create", method = RequestMethod.POST)
	public ModelAndView scholarshipCreatePost(@ModelAttribute Scholarship scholarship, HttpSession session) {
		if(session == null){
			return new ModelAndView("redirect:/login");
		}else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))){
			return new ModelAndView("redirect:/login");
		}
		scholarshipRepository.save(scholarship);
		return new ModelAndView("redirect:/scholarships");
	}
}
