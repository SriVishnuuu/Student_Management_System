package com.vishnu.studentmanagement.ServiceImpl;

import com.vishnu.studentmanagement.Dto.StudentsDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Entity.Enrollments;
import com.vishnu.studentmanagement.Entity.Students;
import com.vishnu.studentmanagement.Repository.StudentRepository;
import com.vishnu.studentmanagement.Service.StudentService;
import com.vishnu.studentmanagement.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper mapper;

    @Override
    public Page<StudentsDTO> getAllStudents(int page, int size) {
        PageRequest pageRequest =  PageRequest.of(page,size);
        return studentRepository.findByActiveTrue(pageRequest).map((student) -> mapper.toDto(student));
    }

    @Override
    public StudentsDTO addStudent(StudentsDTO dto) {
        Students student = mapper.toEntity(dto);
        studentRepository.save(student);
        return mapper.toDto(student);
    }

    @Override
    public StudentsDTO getStudentById(Long id) {
        Students student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return mapper.toDto(student);
    }

    @Override
    public StudentsDTO editStudentById(Long id, StudentsDTO dto) {
        Students student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setActive(dto.isActive());

        Students edited = studentRepository.save(student);
        return mapper.toDto(edited);
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public List<StudentsDTO> getStudents() {
        return studentRepository.findAllByActiveTrue().stream().map(student -> mapper.toDto(student)).toList();
    }

    @Override
    public List<Courses> getEnrollmentsById(Long id) {
        Students student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return student.getEnrollments().stream().map(enrollment -> enrollment.getCourse()).toList();
    }
}
