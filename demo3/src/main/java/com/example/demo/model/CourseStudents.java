package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_students")
public class CourseStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false)
    private Users student;

    public CourseStudents() {}

    public CourseStudents(Course course, Users student) {
        if (!student.getRole().equals(Roles.STUDENT)) {
            throw new IllegalArgumentException("Seuls les étudiants (ETDxxx) peuvent être inscrits à un cours.");
        }
        this.course = course;
        this.student = student;
    }

    public void setStudent(Users student) {
        if (!student.getRole().equals(Roles.STUDENT)) {
            throw new IllegalArgumentException("Seuls les étudiants (ETDxxx) peuvent être inscrits à un cours.");
        }
        this.student = student;
    }

}


