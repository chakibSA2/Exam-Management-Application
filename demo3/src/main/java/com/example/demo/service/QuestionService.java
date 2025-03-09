package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.Question;
import com.example.demo.model.Users;
import com.example.demo.repository.ExamRepository;
import com.example.demo.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    public Question addQuestion(Question question) {
        Optional<Exam> examOpt = examRepository.findById(question.getExamId());
        if (examOpt.isEmpty()) {
            throw new EntityNotFoundException("Professeur introuvable avec ID : " + question.getExamId());
        }
        question.setExam(examOpt.get());
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }

    public List<Question> getQuestionsByExam(Long examId) {
        return questionRepository.findQuestionsByExam(examId);
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question updateQuestion(Question question) {
        Question existingQuestion = questionRepository.findById(question.getId())
                .orElseThrow(() -> new EntityNotFoundException("Question non trouvée"));

        existingQuestion.setQuestionTitle(question.getQuestionTitle());
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
        existingQuestion.setOption1(question.getOption1());
        existingQuestion.setOption2(question.getOption2());
        existingQuestion.setOption3(question.getOption3());
        existingQuestion.setOption4(question.getOption4());
        existingQuestion.setRightAnswer(question.getRightAnswer());
        existingQuestion.setExam(question.getExam());

        return questionRepository.save(existingQuestion);
    }

    public boolean deleteQuestionById(Long questionId) {
        if (questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
            return true;
        }
        return false;
    }
}
