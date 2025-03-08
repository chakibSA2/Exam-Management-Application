package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "option_1")
    private String option1;

    @Column(name = "option_2")
    private String option2;

    @Column(name = "option_3")
    private String option3;

    @Column(name = "option_4")
    private String option4;

    @Column(name = "question_title", nullable = false)
    private String questionTitle;

    @Column(name = "right_answer", nullable = false)
    private String rightAnswer;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id", nullable = false)
    private Exam exam;

    public Question() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
