package com.vishnu.studentmanagement.Controller;


import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentDetailsDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentsDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Entity.Students;
import com.vishnu.studentmanagement.Repository.EnrollmentsRepository;
import com.vishnu.studentmanagement.Repository.StudentRepository;
import com.vishnu.studentmanagement.ServiceImpl.CourseServiceImpl;
import com.vishnu.studentmanagement.ServiceImpl.EnrollmentServiceImpl;
import com.vishnu.studentmanagement.ServiceImpl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private EnrollmentServiceImpl enrollmentService;

    @GetMapping
    public String showEnroll(Model model){
        model.addAttribute("enrollmentDto", new EnrollmentsDTO());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("students", studentService.getStudents());

        return "enroll-course";
    }

    @GetMapping("/list")
    public String getEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model,
    @RequestParam(value="message" , required = false) String message) {

        Page<EnrollmentSummaryDTO> enrollmentPage =
                enrollmentService.getEnrollmentSummary(page,size);

        model.addAttribute("enrollments", enrollmentPage.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", enrollmentPage.getTotalPages());

        model.addAttribute("totalItems", enrollmentPage.getTotalElements());

        model.addAttribute("message", message);

        return "enrolled-students";
    }

    @PostMapping("enroll-course")
    public String saveEnrollment(@Valid @ModelAttribute("enrollmentDto") EnrollmentsDTO enrollmentsDTO,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("students", studentService.getStudents());
            return "enroll-course";
        }
        enrollmentService.enrollCourseToStudent(enrollmentsDTO);

        redirectAttributes.addAttribute("message","enrollment successful");

        return "redirect:/enrollments/list";
    }

    @GetMapping("/enrollment-details/{id}")
    public String viewEnrollmentDetails(@PathVariable Long id,
                                        @RequestParam(defaultValue = "enrolled-students") String from,
                                        Model model){
        EnrollmentDetailsDTO enrollment =
                enrollmentService.getEnrollmentDetails(id);

        model.addAttribute("enrollment", enrollment);
        model.addAttribute("from", from);


        return "enrollment-details";
    }


}
