package com.example.demo.controlleur;

import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/create/{examId}")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestionToExam(examId, question));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Optional<Question> question = questionService.getQuestionById(questionId);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<Question>> getQuestionsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(questionService.getQuestionsByExam(examId));
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<String> deleteExamById(@PathVariable Long questionId) {
        boolean isDeleted = questionService.deleteQuestionById(questionId);
        if (isDeleted) {
            return ResponseEntity.ok("Question supprimé avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
