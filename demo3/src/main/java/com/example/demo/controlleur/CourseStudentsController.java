package com.example.demo.controlleur;

import com.example.demo.model.CourseStudents;
import com.example.demo.service.CourseStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-students")
public class CourseStudentsController {

    @Autowired
    private CourseStudentsService courseStudentsService;

    @PostMapping("/register")
    public ResponseEntity<CourseStudents> registerStudentToCourse(@RequestBody CourseStudents courseStudent) {
        return ResponseEntity.ok(courseStudentsService.registerStudentToCourse(courseStudent));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseStudents>> getStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseStudentsService.getStudentsByCourse(courseId));
    }
}
