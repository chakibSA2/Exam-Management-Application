package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.ExamRepository;
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
    private UserService userService;

    public Exam addTeacherToExam(String userID, Exam exam) {
        Optional<Users> user = userService.getUserById(userID);
        if (user.isPresent()) {
            if (!user.get().getRole().equals(Roles.TEACHER)) {
                exam.setTeacher(user.get());
                return examRepository.save(exam);
            } else {
                throw new IllegalArgumentException("L'utilisateur n'a pas le bon rôle pour être attribué a un examen");
            }
        } else {
            throw new IllegalArgumentException("Utilisateur introuvable pour l'ID : " + userID);
        }
    }

    public Exam addExam(Exam exam) {
        if (!userService.isUserRole(exam.getTeacher().getUserId(), Roles.ADMIN)) {
            throw new IllegalArgumentException("Seuls le personnel d'administration peut créer des examens.");
        }
        return examRepository.save(exam);
    }

    public Exam updateExam(Exam exam) {
        // Vérifier si l'examen existe déjà
        Optional<Exam> existingExamOpt = examRepository.findById(exam.getId());

        if (existingExamOpt.isEmpty()) {
            throw new EntityNotFoundException("Examen non trouvé avec l'ID : " + exam.getId());
        }

        Exam existingExam = existingExamOpt.get();

        // Mettre à jour uniquement les champs nécessaires
        existingExam.setTitle(exam.getTitle());
        existingExam.setDate(exam.getDate());
        existingExam.setDate(exam.getDate());
        existingExam.setCourse(exam.getCourse());

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
