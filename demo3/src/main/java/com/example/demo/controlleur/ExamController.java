package com.example.demo.controlleur;

import com.example.demo.model.Exam;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        Exam newExam = examService.addExam(exam);
        return ResponseEntity.ok(newExam);
    }

    @GetMapping("/")
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Exam>> getExamsByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(examService.getExamsByTeacher(teacherId));
    }
}
