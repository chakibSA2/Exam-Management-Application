package com.example.demo.controlleur;

import com.example.demo.model.ExamStudents;
import com.example.demo.service.ExamStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-students")
public class ExamStudentsController {

    @Autowired
    private ExamStudentsService examStudentsService;

    @PostMapping("/register")
    public ResponseEntity<ExamStudents> registerStudent(@RequestBody ExamStudents examStudent) {
        return ResponseEntity.ok(examStudentsService.registerStudentToExam(examStudent));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamStudents>> getStudentsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(examStudentsService.getStudentsByExam(examId));
    }
}
