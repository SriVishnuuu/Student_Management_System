package com.vishnu.studentmanagement.Service;

import com.vishnu.studentmanagement.Dto.StudentsDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Entity.Enrollments;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Page<StudentsDTO> getAllStudents(int page, int size);

    StudentsDTO addStudent(StudentsDTO dto);

    StudentsDTO getStudentById(Long id);

    StudentsDTO editStudentById(Long id,StudentsDTO dto);

    boolean existsByEmail(String email);

    List<StudentsDTO> getStudents();

    List<Courses> getEnrollmentsById(Long id);

}
