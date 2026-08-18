package com.vishnu.studentmanagement.Service;

import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {

     CoursesDTO saveCourse(CoursesDTO courseDTO);

     boolean existsByCode(String code);

     boolean existsByCodeAndIdNot(String code, Long id);

     Page<CoursesDTO> getCourses(int page, int size);

     CoursesDTO getCourseById(Long id);

     CoursesDTO updateCourse(Long id, CoursesDTO courseDTO);

     List<CoursesDTO> getAllCourses();

}
