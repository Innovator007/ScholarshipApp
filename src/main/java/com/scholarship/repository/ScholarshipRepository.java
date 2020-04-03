package com.scholarship.repository;

import com.scholarship.model.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Integer> {

    List<Scholarship> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
    
    List<Scholarship> findByAmountGreaterThan(int amount);

    List<Scholarship> findByAmountLessThan(int amount);

    Scholarship findById(int id);

    Scholarship deleteById(int id);

}