package com.example.demo.controlleur;

import com.example.demo.model.Exam;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        Optional<Exam> exam = examService.getExamById(examId);
        return exam.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/teacher/{teacherId}/All")
    public ResponseEntity<List<Exam>> getExamsByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(examService.getExamsByTeacher(teacherId));
    }

    @GetMapping("/teacher/{teacherId}/First")
    public ResponseEntity<Exam> getExamByTeacher(@PathVariable String teacherId) {
        return ResponseEntity.ok(examService.getExamByTeacher(teacherId));
    }

    @PutMapping("/update")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.updateExam(exam));
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteExamById(@PathVariable Long examId) {
        boolean isDeleted = examService.deleteExamById(examId);
        if (isDeleted) {
            return ResponseEntity.ok("Exam supprimé avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{examId}/add-student/{studentId}")
    public ResponseEntity<String> addStudentToExam(@PathVariable Long examId, @PathVariable String studentId) {
        examService.addStudentToExam(examId, studentId);
        return ResponseEntity.ok("Étudiant ajouté à l'examen avec succès.");
    }

    @DeleteMapping("/{examId}/remove-student/{studentId}")
    public ResponseEntity<String> removeStudentFromExam(@PathVariable Long examId, @PathVariable String studentId) {
        examService.removeStudentFromExam(examId, studentId);
        return ResponseEntity.ok("Étudiant supprimé de l'examen avec succès.");
    }
}
