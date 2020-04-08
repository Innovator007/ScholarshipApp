package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="`student`")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 100)
    private String school;    

    @Size(max = 100)
    private String studentId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Student() { }

    public Student(String phoneNumber, String school, String studentId) {
    	this.setPhoneNumber(phoneNumber);
        this.setSchool(school);
        this.setStudentId(studentId);
    }

    public Student(int id, String phoneNumber, String school, String studentId) {
    	this.setId(id);
    	this.setPhoneNumber(phoneNumber);
        this.setSchool(school);
        this.setStudentId(studentId);
    }

    public int getId() {
    	return id;
    }

    public String getPhoneNumber() {
    	return phoneNumber;
    }

    public String getSchool() {
    	return school;
    }

    public String getStudentId() {
    	return studentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSchool(String school) {
    	this.school = school;
    }

    public void setStudentId(String studentId) {
    	this.studentId = studentId;
    }
    
}
