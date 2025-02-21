package com.example.demo.repository;

import com.example.demo.model.CourseStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseStudentsRepository extends JpaRepository<CourseStudents, Long> {

    @Query("SELECT cs FROM CourseStudents cs WHERE cs.course.id = :courseId")
    List<CourseStudents> findStudentsByCourse(@Param("courseId") Long courseId);
}
