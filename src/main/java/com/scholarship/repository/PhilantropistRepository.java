package com.scholarship.repository;

import com.scholarship.model.Philantropist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhilantropistRepository extends JpaRepository<Philantropist, Integer> {

}