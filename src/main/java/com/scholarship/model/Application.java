package com.scholarship.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="`application`")
public class Application implements Serializable {

    public Application() { }

    public Application(String aboutyourself) {
        this.setAboutyourself(aboutyourself);
    }

    public Application(int id, String aboutyourself) {
        this.setId(id);
        this.setAboutyourself(aboutyourself);
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

    @NotNull
    private String aboutyourself;

    public String getAboutyourself() {
        return aboutyourself;
    }

    public void setAboutyourself(String aboutyourself) {
        this.aboutyourself = aboutyourself;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "scholarship_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scholarship scholarship;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    public Scholarship getScholarship() {
        return scholarship;
    }

    public Student getStudent() {
        return student;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
