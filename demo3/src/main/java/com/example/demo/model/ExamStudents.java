package com.example.demo.model;
import jakarta.persistence.*;
@Entity
@Table(name = "exam_students")
public class ExamStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id", nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", nullable = false)
    private Users student;

    public ExamStudents() {}

    public ExamStudents(Exam exam, Users student) {
        if (!student.getRole().equals(Roles.STUDENT)) {
            throw new IllegalArgumentException("Seuls les étudiants (ETDxxx) peuvent être inscrits à un examen.");
        }
        this.exam = exam;
        this.student = student;
    }

    public void setStudent(Users student) {
        if (!student.getRole().equals(Roles.STUDENT)) {
            throw new IllegalArgumentException("Seuls les étudiants (ETDxxx) peuvent être inscrits à un examen.");
        }
        this.student = student;
    }

}

