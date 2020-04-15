package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="`philantropist`")
public class Philantropist implements Serializable {

    public Philantropist() { }

    public Philantropist(String phonenumber, String institute, String philantropistid) {
        this.setPhonenumber(phonenumber);
        this.setInstitute(institute);
        this.setPhilantropistid(philantropistid);
    }

    public Philantropist(int id, String phonenumber, String institute, String philantropistid) {
        this.setId(id);
        this.setPhonenumber(phonenumber);
        this.setInstitute(institute);
        this.setPhilantropistid(philantropistid);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phonenumber")
    @Size(max = 15)
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public User getUser(){
        return user;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Size(max = 100)
    private String institute;

    public String getInstitute() {
        return institute;
    }    

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Size(max = 100)
    private String philantropistid;

    public String getPhilantropistid() {
        return philantropistid;
    }

    public void setPhilantropistid(String philantropistid) {
        this.philantropistid = philantropistid;
    }

    public void setUser(User user){
        this.user = user;
    }

    @OneToMany(mappedBy = "philantropist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Scholarship> scholarships;

    public Set<Scholarship> getScholarships() {
        return scholarships;
    }

    public void setScholarships(Set<Scholarship> scholarships) {
        this.scholarships = scholarships;
    }
  
}
