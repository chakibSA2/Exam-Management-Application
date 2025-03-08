package com.example.demo.controlleur;

import com.example.demo.model.Quiz;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuizById(@PathVariable Long quizId) {
        boolean isDeleted = quizService.deleteQuizById (quizId);
        if (isDeleted) {
            return ResponseEntity.ok("Question supprimé avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(quizId, quiz));
    }

}
