package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.Users;
import com.example.demo.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserService userService;

    public Exam addExam(Exam exam) {
        if (!userService.isUserRole(exam.getTeacher().getUserId(), "TEACHER")) {
            throw new IllegalArgumentException("Seuls les enseignants (ENSxxx) peuvent créer des examens.");
        }
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> getExamsByTeacher(String teacherId) {
        return examRepository.findExamsByTeacher(teacherId);
    }
}
