package com.example.demo.repository;

import com.example.demo.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    @Query("SELECT qq FROM QuizQuestion qq WHERE qq.quiz.id = :quizId")
    List<QuizQuestion> findQuestionsByQuiz(@Param("quizId") Long quizId);
}
