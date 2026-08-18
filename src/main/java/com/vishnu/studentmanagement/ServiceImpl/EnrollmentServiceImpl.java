package com.vishnu.studentmanagement.ServiceImpl;

import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentDetailsDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentsDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Entity.Enrollments;
import com.vishnu.studentmanagement.Entity.Students;
import com.vishnu.studentmanagement.Repository.CourseRepository;
import com.vishnu.studentmanagement.Repository.EnrollmentsRepository;
import com.vishnu.studentmanagement.Repository.StudentRepository;
import com.vishnu.studentmanagement.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void enrollCourseToStudent(EnrollmentsDTO dto) {
        Students student = studentRepository.findById(dto.getStudentId()).orElseThrow(RuntimeException::new);
        for(Long courseId : dto.getCourseIds()){
            Courses course = courseRepository.findById(courseId).orElseThrow(RuntimeException::new);

            if(enrollmentsRepository.existsByStudentIdAndCourseId(student.getId(),courseId)){
                continue;
            }

            Enrollments enrollments = new Enrollments();
            enrollments.setCourse(course);
            enrollments.setStudent(student);

            enrollmentsRepository.save(enrollments);
        }

    }

    @Override
    public Page<EnrollmentSummaryDTO> getEnrollmentSummary(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return enrollmentsRepository.findEnrollmentSummary(pageRequest);
    }

    @Override
    public EnrollmentDetailsDTO getEnrollmentDetails(Long id) {
        List<Enrollments> enrollments =
                enrollmentsRepository.findEnrollmentsByStudentId(id);

        if (enrollments.isEmpty()) {
            throw new RuntimeException("Student not found.");
        }

        Students student = enrollments.get(0).getStudent();

        EnrollmentDetailsDTO dto = new EnrollmentDetailsDTO();

        dto.setStudentId(student.getId());
        dto.setStudentName(student.getFirstName() + " " + student.getLastName());
        dto.setStudentEmail(student.getEmail());

        dto.setTotalCourses((long) enrollments.size());

        BigDecimal totalFee = BigDecimal.ZERO;

        List<CoursesDTO> courses = new ArrayList<>();

        for (Enrollments enrollment : enrollments) {

            Courses course = enrollment.getCourse();

            CoursesDTO courseDTO = new CoursesDTO();

            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setFee(course.getFee());

            courses.add(courseDTO);

            totalFee = totalFee.add(course.getFee());
        }

        dto.setCourses(courses);
        dto.setTotalFee(totalFee);

        return dto;
    }

    @Override
    public List<EnrollmentSummaryDTO> getRecentEnrollments() {
        PageRequest pageRequest = PageRequest.of(0,5);
        return enrollmentsRepository.findRecentEnrollments(pageRequest).getContent();

    }
}
