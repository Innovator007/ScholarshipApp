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
		String role = "";
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 1) {
				role = "philantropist";
			} else {
				role = "student";
			}
		}
		List<Scholarship> scholarships = scholarshipRepository.findAll();
		return new ModelAndView("scholarships").addObject("scholarships", scholarships).addObject("role", role);
	}

	@RequestMapping(value = "/scholarship/create")
	public ModelAndView scholarshipCreate(HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		String role;
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 1) {
				role = "philantropist";
				return new ModelAndView("createScholarshipForm").addObject("role", role);
			} else {
				return new ModelAndView("redirect:/scholarships");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/scholarship/{id}/edit")
	public ModelAndView scholarshipEdit(@PathVariable String id, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		String role = "";
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 1) {
				role = "philantropist";
				int scholarshipId = Integer.parseInt(id);
				Philantropist philantropist = user.getPhilantropist();
				Scholarship scholarship = scholarshipRepository.findByIdAndPhilantropist(scholarshipId, philantropist);
				if (scholarship != null) {
					return new ModelAndView("editScholarship").addObject("scholarship", scholarship).addObject("role", role);
				} else {
					return new ModelAndView("redirect:/philantropist/scholarships");
				}
			} else {
				role = "student";
				return new ModelAndView("redirect:/scholarships");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/scholarship/{id}/edit", method = RequestMethod.POST)
	public ModelAndView scholarshipEditPost(@PathVariable String id, @ModelAttribute Scholarship scholarship) {
		int scholarshipId = Integer.parseInt(id);
		Scholarship scholarshipData = scholarshipRepository.findById(scholarshipId);

		scholarshipData.setTitle(scholarship.getTitle());
		scholarshipData.setDescription(scholarship.getDescription());
		scholarshipData.setDeadline(scholarship.getDeadlineString());
		scholarshipData.setAmount(scholarship.getAmount());

		scholarshipRepository.save(scholarshipData);

		return new ModelAndView("redirect:/philantropist/scholarships");
	}

	@RequestMapping(value = "/scholarship/{id}/view")
	public ModelAndView scholarshipView(@PathVariable String id, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		int scholarshipId = Integer.parseInt(id);
		String role = "";
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 1) {
				role = "philantropist";
			} else {
				role = "student";
			}
		}
		Scholarship scholarship = scholarshipRepository.findById(scholarshipId);
		List<Application> applications = applicationRepository.findByScholarship(scholarship);
		return new ModelAndView("scholarshipdetail").addObject("scholarship", scholarship).addObject("totalApplication", applications.size()).addObject("role", role);
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
		return new ModelAndView("redirect:/philantropist/scholarships");
	}

	@RequestMapping(value = "/scholarship/{id}/apply")
	public ModelAndView scholarshipApply(@PathVariable String id, HttpSession session) {
		if(!CheckLoggedIn.isLoggedIn(session)){
			return new ModelAndView("redirect:/login");
		}
		User user = (User)session.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 2) {
				Scholarship scholarship = scholarshipRepository.findById(Integer.parseInt(id));
				if (scholarship != null) {
					return new ModelAndView("applyForm").addObject("scholarshipAction", "/scholarship/" + scholarship.getId() + "/apply").addObject("role", "student");
				} else {
					return new ModelAndView("redirect:/scholarships");
				}
			} else {
				return new ModelAndView("redirect:/philantropist/scholarships");
			}
		} else {
			return new ModelAndView("redirect:/login");
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
					Application prevApplication = applicationRepository.findByStudentAndScholarship(student, scholarship);
					if (prevApplication == null) {
						applicationRepository.save(application);
					} else {
						return new ModelAndView("redirect:/student/applications");
					}
				}
			}
		}
		return new ModelAndView("redirect:/philantropist/scholarships");
	}
}
