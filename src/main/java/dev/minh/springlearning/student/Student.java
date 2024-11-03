package dev.minh.springlearning.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.minh.springlearning.StudentProfile;
import dev.minh.springlearning.school.School;
import jakarta.persistence.*;

@Entity
@Table(name="T_STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(
            name="fName",
            length = 20
    )
    private String firstname;
    private String lastname;

    @Column(
            unique = true
    )
    private String email;
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
