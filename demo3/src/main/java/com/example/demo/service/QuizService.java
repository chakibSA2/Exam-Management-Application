package com.example.demo.service;

import com.example.demo.model.Quiz;
import com.example.demo.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Quiz> getQuizById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    public Quiz updateQuiz(Long quizId, Quiz quiz) {
        Quiz existingQuiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz non trouvé avec l'ID : " + quizId));

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
