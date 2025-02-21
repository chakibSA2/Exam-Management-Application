package com.example.demo.service;

import com.example.demo.model.CourseStudents;
import com.example.demo.repository.CourseStudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStudentsService {

    @Autowired
    private CourseStudentsRepository courseStudentsRepository;

    public CourseStudents registerStudentToCourse(CourseStudents courseStudent) {
        return courseStudentsRepository.save(courseStudent);
    }

    public List<CourseStudents> getStudentsByCourse(Long courseId) {
        return courseStudentsRepository.findStudentsByCourse(courseId);
    }
}
