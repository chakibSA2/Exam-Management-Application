package com.example.demo.service;

import com.example.demo.model.Quiz;
import com.example.demo.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz updateQuiz(Quiz quiz) {
        Quiz existingQuiz = quizRepository.findById(quiz.getId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz non trouvé"));

        existingQuiz.setTitle(quiz.getTitle());

        return quizRepository.save(existingQuiz);
    }

    public boolean deleteQuizById(Long quizId) {
        if (quizRepository.existsById(quizId)) {
            quizRepository.deleteById(quizId);
            return true;
        }
        return false;
    }
}
