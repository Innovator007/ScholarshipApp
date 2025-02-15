package com.scholarship.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Locale;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

@Entity
@Table(name="`scholarship`")
public class Scholarship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @NotNull
    private int amount;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "philantropist_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Philantropist philantropist;

    @OneToMany(mappedBy = "scholarship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Application> applications;


    public Scholarship() { }

    public Scholarship(String title, String description, int amount, String deadline) {
    	this.setTitle(title);
    	this.setDescription(description);
    	this.setAmount(amount);
        this.setDeadline(deadline);
    }

    public Scholarship(int id, String title, String description, int amount, String deadline) {
    	this.setId(id);
    	this.setTitle(title);
    	this.setDescription(description);
    	this.setAmount(amount);
        this.setDeadline(deadline);
    }

    public int getId() {
    	return id;
    }

    public String getTitle() {
    	return title;
    }

    public String getDescription() {
    	return description;
    }

    public String getDeadlineString() {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String stringDeadline = df.format(deadline);
        return stringDeadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getAmount() {
    	return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

    public void setAmount(int amount) {
    	this.amount = amount;
    }

    public void setDeadline(String deadline) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(deadline, formatter);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        this.deadline = date;
    }
    
    @JsonIgnore
    public Philantropist getPhilantropist() {
        return philantropist;
    }

    @JsonIgnore
    public void setPhilantropist(Philantropist philantropist) {
        this.philantropist = philantropist;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "Scholarship {" +
                "Id=" + id +
                ", Title='" + title + '\'' +
                ", Description='" + description + '\'' +
                ", Amount='" + amount + '\'' +
                ", Deadline='" + deadline + '\'' +
                '}';
    }

}
