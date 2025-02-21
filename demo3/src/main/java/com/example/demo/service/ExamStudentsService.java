package com.example.demo.service;

import com.example.demo.model.ExamStudents;
import com.example.demo.repository.ExamStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamStudentsService {

    @Autowired
    private ExamStudentsRepository examStudentsRepository;

    public ExamStudents registerStudentToExam(ExamStudents examStudent) {
        return examStudentsRepository.save(examStudent);
    }

    public List<ExamStudents> getStudentsByExam(Long examId) {
        return examStudentsRepository.findStudentsByExam(examId);
    }
}
