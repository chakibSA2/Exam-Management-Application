package com.example.demo.repository;

import com.example.demo.model.ExamStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamStudentsRepository extends JpaRepository<ExamStudents, Long> {

    @Query("SELECT es FROM ExamStudents es WHERE es.exam.id = :examId")
    List<ExamStudents> findStudentsByExam(@Param("examId") Long examId);
}
