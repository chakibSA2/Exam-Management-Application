package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "exam_title", nullable = false)
    private String examTitle;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id", nullable = false)
    private Users teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @Transient // ✅ Champ utilisé pour récupérer l'ID du prof depuis le front
    private String teacherId;

    @Transient // ✅ Champ utilisé pour récupérer l'ID du cours depuis le front
    private Long courseId;

    public Exam() {}

    public Exam(String examTitle, LocalDateTime date, Users teacher, Course course) {
        if (!teacher.getRole().equals(Roles.TEACHER)) {
            throw new IllegalArgumentException("Seuls les enseignants (ENSxxx) peuvent être assignés à un examen.");
        }
        this.examTitle = examTitle;
        this.date = date;
        this.teacher = teacher;
        this.course = course;
    }

    // ✅ Ajout de la gestion des IDs transients pour faciliter la liaison avec le front
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    // ✅ Garder tous les getters et setters existants
    public void setTeacher(Users teacher) {
        if (!teacher.getRole().equals(Roles.TEACHER)) {
            throw new IllegalArgumentException("Seuls les enseignants (ENSxxx) peuvent être assignés à un examen.");
        }
        this.teacher = teacher;
    }

    public Users getTeacher() {
        return teacher;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return examTitle;
    }

    public void setTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
