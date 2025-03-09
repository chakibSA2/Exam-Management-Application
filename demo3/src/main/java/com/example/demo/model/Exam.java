package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transient
    private String teacherId;

    @Transient
    private Long courseId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "exams_students",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    public List<Users> studentsExams;

    public Exam() {
    }

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

    public List<Users> getStudentsExams() {
        return studentsExams;
    }

    public void setStudentsExams(List<Users> studentsExams) {
        this.studentsExams = studentsExams;
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
