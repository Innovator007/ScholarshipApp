package com.scholarship.controller;

import com.scholarship.model.Scholarship;
import com.scholarship.repository.ScholarshipRepository;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class ScholarshipController {

	@Autowired
    ScholarshipRepository scholarshipRepository;

    @GetMapping("/scholarship")
    public List<Scholarship> index() {
    	return scholarshipRepository.findAll();
    }

    @GetMapping("/scholarship/{id}")
    public Scholarship show(@PathVariable String id) {
    	int scholarshipId = Integer.parseInt(id);
    	return scholarshipRepository.findById(scholarshipId);
    }

    @PostMapping("/scholarship/search")
    public List<Scholarship> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return scholarshipRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/scholarship/search/amount")
    public List<Scholarship> searchAmount(@RequestBody Map<String, Integer> body){
        int searchTerm = body.get("amount");
        int isGreater = body.get("isGreater");
        if(isGreater == 1) {
            return scholarshipRepository.findByAmountGreaterThan(searchTerm);
        } else {
            return scholarshipRepository.findByAmountLessThan(searchTerm);
        }
    }

    @PostMapping("/scholarship")
    public Scholarship create(@RequestBody Scholarship body) {
    	return scholarshipRepository.save(body);
    }

    @PutMapping("/scholarship/{id}")
    public Scholarship update(@PathVariable String id, @RequestBody Scholarship body) {
        int scholarshipId = Integer.parseInt(id);
        Scholarship scholarshipData = scholarshipRepository.findById(scholarshipId);
        scholarshipData.setTitle(body.getTitle());
        scholarshipData.setDescription(body.getDescription());
        scholarshipData.setAmount(body.getAmount());
        return scholarshipRepository.save(scholarshipData);
    }

    @DeleteMapping("/scholarship/{id}")
    public boolean delete(@PathVariable String id) {
        int scholarshipId = Integer.parseInt(id);
        scholarshipRepository.deleteById(scholarshipId);
        return true;
    }

}
