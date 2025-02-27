package com.example.demo.model;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "exam_title", nullable = false)
    private String examTitle;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id", nullable = false)
    private Users teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    public Exam() {}

    public Exam(String examTitle, Users teacher, Course course) {
        if (!teacher.getRole().equals(Roles.TEACHER)) {
            throw new IllegalArgumentException("Seuls les enseignants (ENSxxx) peuvent être assignés à un examen.");
        }
        this.examTitle = examTitle;
        this.teacher = teacher;
        this.course = course;
    }

    public void setTeacher(Users teacher) {
        if (!teacher.getRole().equals(Roles.TEACHER)) {
            throw new IllegalArgumentException("Seuls les enseignants (ENSxxx) peuvent être assignés à un examen.");
        }
        this.teacher = teacher;
    }

    public Users getTeacher() {
        return teacher;
    }
}
