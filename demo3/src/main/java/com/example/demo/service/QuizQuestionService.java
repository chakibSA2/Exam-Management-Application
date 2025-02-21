package com.example.demo.service;

import com.example.demo.model.QuizQuestion;
import com.example.demo.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    public QuizQuestion addQuestionToQuiz(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }
    public List<QuizQuestion> getQuestionsByQuiz(Long quizId) {
        return quizQuestionRepository.findQuestionsByQuiz(quizId);
    }
}
