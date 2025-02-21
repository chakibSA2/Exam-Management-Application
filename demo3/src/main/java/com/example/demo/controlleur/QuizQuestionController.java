package com.example.demo.controlleur;

import com.example.demo.model.QuizQuestion;
import com.example.demo.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-questions")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    @PostMapping("/add")
    public ResponseEntity<QuizQuestion> addQuestionToQuiz(@RequestBody QuizQuestion quizQuestion) {
        return ResponseEntity.ok(quizQuestionService.addQuestionToQuiz(quizQuestion));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizQuestion>> getQuestionsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizQuestionService.getQuestionsByQuiz(quizId));
    }
}
