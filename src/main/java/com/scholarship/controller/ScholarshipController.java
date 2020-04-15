package com.scholarship.controller;

import com.scholarship.model.User;
import com.scholarship.model.Student;
import com.scholarship.model.Philantropist;
import com.scholarship.model.Scholarship;
import com.scholarship.repository.ScholarshipRepository;
import com.scholarship.model.Application;
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
import com.scholarship.utils.CheckLoggedIn;

@Controller
public class ScholarshipController {
   
	@Autowired
    ScholarshipRepository scholarshipRepository;

    @Autowired
    ApplicationRepository applicationRepository;


	@RequestMapping(value = "/scholarships")
	public ModelAndView scholarshipIndex(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		List<Scholarship> scholarships = scholarshipRepository.findAll();
		return new ModelAndView("scholarships").addObject("scholarships", scholarships);
	}

	@RequestMapping(value = "/scholarship/create")
	public ModelAndView scholarshipCreate(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("createScholarshipForm");
	}

	@RequestMapping(value = "/scholarship/edit")
	public ModelAndView scholarshipEdit(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("editScholarship");
	}

	@RequestMapping(value = "/scholarship/view/{id}")
	public ModelAndView scholarshipView(@PathVariable String id, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		int scholarshipId = Integer.parseInt(id);
		Scholarship scholarship = scholarshipRepository.findById(scholarshipId);
		return new ModelAndView("scholarshipdetail").addObject("scholarship", scholarship);
	}

	@RequestMapping(value = "/scholarship/create", method = RequestMethod.POST)
	public ModelAndView scholarshipCreatePost(@ModelAttribute Scholarship scholarship, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		User user = (User)session.getAttribute("user");
		if (user.getRole() == 1) {
			Philantropist philantropist = user.getPhilantropist();
			scholarship.setPhilantropist(philantropist);
			scholarshipRepository.save(scholarship);
		}
		return new ModelAndView("redirect:/scholarships");
	}

	@RequestMapping(value = "/scholarship/{id}/apply")
	public ModelAndView scholarshipApply(@PathVariable String id, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		Scholarship scholarship = scholarshipRepository.findById(Integer.parseInt(id));
		if (scholarship != null) {
			return new ModelAndView("applyForm").addObject("scholarshipId", scholarship.getId());
		} else {
			return new ModelAndView("redirect:/scholarships");
		}
	}

	@RequestMapping(value = "/scholarship/{id}/apply", method = RequestMethod.POST)
	public ModelAndView scholarshipApplyPost(@PathVariable String id, @ModelAttribute Application application, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		User user = (User)session.getAttribute("user");
		if (user.getRole() == 2) {
			Student student = user.getStudent();
			if (student != null) {
				application.setStudent(student);
				Scholarship scholarship = scholarshipRepository.findById(Integer.parseInt(id));
				if (scholarship != null) {
					application.setScholarship(scholarship);
					applicationRepository.save(application);
				}
			}
		}
		return new ModelAndView("redirect:/scholarships");
	}
}
