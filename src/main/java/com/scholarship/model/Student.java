package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="`student`")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phonenumber")
    @Size(max = 15)
    private String phonenumber;

    @Size(max = 100)
    private String school;    

    @Size(max = 100)
    private String studentid;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Student() { }

    public Student(String phonenumber, String school, String studentid) {
    	this.setPhonenumber(phonenumber);
        this.setSchool(school);
        this.setStudentid(studentid);
    }

    public Student(int id, String phonenumber, String school, String studentid) {
    	this.setId(id);
    	this.setPhonenumber(phonenumber);
        this.setSchool(school);
        this.setStudentid(studentid);
    }

    public int getId() {
    	return id;
    }

    public String getPhonenumber() {
    	return phonenumber;
    }

    public String getSchool() {
    	return school;
    }

    public String getStudentid() {
    	return studentid;
    }

    public User getUser(){
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setSchool(String school) {
    	this.school = school;
    }

    public void setStudentid(String studentid) {
    	this.studentid = studentid;
    }

    public void setUser(User user){
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "Student {" +
            "StudentId: " + studentid + '\'' +
            "}";    
    }

}
