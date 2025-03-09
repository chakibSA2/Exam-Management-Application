package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @Column(name = "quiz_title", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "Quiz_question",
            joinColumns = {@JoinColumn(name = "quiz_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    public List<Question> quizes;

    public Quiz() {
    }

    public Quiz(String title) {
        this.title = title;
    }


    public List<Question> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Question> quizes) {
        this.quizes = quizes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}
