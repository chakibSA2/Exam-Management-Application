package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.Course;
import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private CourseRepository courseRepository; // ✅ Ajout du repo des cours

    @Autowired
    private UserRepository userRepository; // ✅ Ajout du repo des users

    public Exam addExam(Exam exam) {
        // ✅ Vérifier si le professeur existe
        Optional<Users> teacherOpt = userRepository.findById(exam.getTeacherId());
        if (teacherOpt.isEmpty()) {
            throw new EntityNotFoundException("Professeur introuvable avec ID : " + exam.getTeacherId());
        }
        exam.setTeacher(teacherOpt.get());

        // ✅ Vérifier si le cours existe
        Optional<Course> courseOpt = courseRepository.findById(exam.getCourseId());
        if (courseOpt.isEmpty()) {
            throw new EntityNotFoundException("Cours introuvable avec ID : " + exam.getCourseId());
        }
        exam.setCourse(courseOpt.get());

        // ✅ Sauvegarde de l'examen avec le professeur et le cours associés
        return examRepository.save(exam);
    }

    public Exam updateExam(Exam exam) {
        Optional<Exam> existingExamOpt = examRepository.findById(exam.getId());

        if (existingExamOpt.isEmpty()) {
            throw new EntityNotFoundException("Examen non trouvé avec l'ID : " + exam.getId());
        }

        Exam existingExam = existingExamOpt.get();

        existingExam.setTitle(exam.getTitle());
        existingExam.setDate(exam.getDate());

        // ✅ Mise à jour du professeur
        Optional<Users> teacherOpt = userRepository.findById(exam.getTeacherId());
        if (teacherOpt.isPresent()) {
            existingExam.setTeacher(teacherOpt.get());
        }

        // ✅ Mise à jour du cours
        Optional<Course> courseOpt = courseRepository.findById(exam.getCourseId());
        if (courseOpt.isPresent()) {
            existingExam.setCourse(courseOpt.get());
        }

        return examRepository.save(existingExam);
    }

    public boolean deleteExamById(Long examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return true;
        }
        return false;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Optional<Exam> getExamById(Long examId) {
        return examRepository.findById(examId);
    }

    public List<Exam> getExamsByTeacher(String teacherId) {
        return examRepository.findExamsByTeacher(teacherId);
    }

    public Exam getExamByTeacher(String teacherId) {
        return examRepository.findExamsByTeacher(teacherId).get(0);
    }
}
