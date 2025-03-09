package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Users;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public boolean deleteCourse(Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public Course updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));

        existingCourse.setTitle(course.getTitle());
        return courseRepository.save(existingCourse);
    }

    public void addStudentToCourse(Long courseId, String studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Cours non trouvé avec l'ID : " + courseId));
        Users student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + studentId));

        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public void removeStudentFromCourse(Long courseId, String studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Cours non trouvé avec l'ID : " + courseId));
        Users student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + studentId));

        course.getStudents().remove(student);
        courseRepository.save(course);
    }
}
