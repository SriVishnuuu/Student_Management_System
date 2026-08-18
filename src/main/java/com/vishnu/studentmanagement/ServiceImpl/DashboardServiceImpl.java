package com.vishnu.studentmanagement.ServiceImpl;

import com.vishnu.studentmanagement.Dto.DashboardDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Repository.CourseRepository;
import com.vishnu.studentmanagement.Repository.EnrollmentsRepository;
import com.vishnu.studentmanagement.Repository.StudentRepository;
import com.vishnu.studentmanagement.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class DashboardServiceImpl implements DashboardService {


    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public DashboardDTO getStudentManagementData() {

        Long totalStudents = studentRepository.count();
        Long totalCourses = courseRepository.count();

        Courses topCourse = courseRepository.findAll()
                .stream()
                .max(Comparator.comparing(courses -> courses.getEnrollments().size()))
                .orElse(null);

        Long enrollmentsInThisMonth = enrollmentsRepository.countEnrollmentsThisMonth();

        DashboardDTO dto = new DashboardDTO();
        dto.setTotalStudents(totalStudents);
        dto.setTotalCourses(totalCourses);
        if(topCourse != null) dto.setTopCourse(topCourse.getCourseName());
        dto.setMonthlyEnrollments(enrollmentsInThisMonth);

        return dto;
    }
}
