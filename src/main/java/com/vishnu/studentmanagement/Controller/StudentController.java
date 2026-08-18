package com.vishnu.studentmanagement.Controller;


import com.vishnu.studentmanagement.Dto.StudentsDTO;
import com.vishnu.studentmanagement.ServiceImpl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/list")
    public String  getStudents(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               @RequestParam(value = "message", required = false) String message
                               ){
        Page< StudentsDTO> students = studentService.getAllStudents(page, size);
        model.addAttribute("students",students);
        model.addAttribute("message",message);
        return "students";
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        model.addAttribute("studentDto", new StudentsDTO());
        return "add-student";
    }


    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("studentDto") StudentsDTO dto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes){
        System.out.println("Address = " + dto.getAddress());
        System.out.println("Has Errors = " + bindingResult.hasErrors());

        if(studentService.existsByEmail(dto.getEmail())){
            bindingResult.rejectValue("email","duplicate.email", "Course code must be unique");
            return "add-student";
        }
        if(bindingResult.hasErrors()){
            return "add-student";
        }
        studentService.addStudent(dto);
        redirectAttributes.addAttribute("message","student is added successfully");

        return "redirect:/students/list";

    }

    @GetMapping("/view/{id}")
    public String viewStudent(@PathVariable Long id, Model model){
        StudentsDTO student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "view-student";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        StudentsDTO student = studentService.getStudentById(id);
        model.addAttribute("studentDto",student);
        return "edit-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                              @Valid @ModelAttribute("studentDto") StudentsDTO dto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            return "edit-student";
        }

        studentService.editStudentById(id,dto);

        redirectAttributes.addAttribute("message", "student details are updated successfully");

        return "redirect:/students/list";
    }


}
