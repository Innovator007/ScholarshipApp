package com.scholarship.controller;

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

@Controller
public class ScholarshipController {
   
	@Autowired
    ScholarshipRepository scholarshipRepository;

    @Autowired
    ApplicationRepository applicationRepository;


	@RequestMapping(value = "/scholarships")
	public ModelAndView scholarshipIndex() {
		List<Scholarship> scholarships = scholarshipRepository.findAll();
		return new ModelAndView("scholarships").addObject("scholarships", scholarships);
	}

	@RequestMapping(value = "/scholarship/create")
	public ModelAndView scholarshipCreate() {
		return new ModelAndView("createScholarshipForm");
	}

	@RequestMapping(value = "/scholarship/edit")
	public ModelAndView scholarshipEdit() {
		return new ModelAndView("editScholarship");
	}

	@RequestMapping(value = "/scholarship/view/{id}")
	public ModelAndView scholarshipView(@PathVariable String id) {
		int scholarshipId = Integer.parseInt(id);
		Scholarship scholarship = scholarshipRepository.findById(scholarshipId);
		return new ModelAndView("scholarshipdetail").addObject("scholarship", scholarship);
	}

	@RequestMapping(value = "/scholarship/create", method = RequestMethod.POST)
	public ModelAndView scholarshipCreatePost(@ModelAttribute Scholarship scholarship) {
		scholarshipRepository.save(scholarship);
		return new ModelAndView("redirect:/scholarships");
	}

	@RequestMapping(value = "/scholarship/apply")
	public ModelAndView scholarshipApply() {
		return new ModelAndView("applyForm");
	}

	@RequestMapping(value = "/scholarship/apply", method = RequestMethod.POST)
	public ModelAndView scholarshipApplyPost(@ModelAttribute Application application) {
		applicationRepository.save(application);
		return new ModelAndView("redirect:/scholarships");
	}
}
