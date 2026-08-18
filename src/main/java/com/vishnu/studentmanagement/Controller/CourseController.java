package com.vishnu.studentmanagement.Controller;


import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.ServiceImpl.CourseServiceImpl;
import com.vishnu.studentmanagement.mapper.CourseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping("/new")
    public String showCreateCourse(Model model){
        model.addAttribute("courseDto", new CoursesDTO());
        return "add-course";
    }
    @GetMapping("/list")
    public String listCourses(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "2") int size,
                              @RequestParam(value = "message", required = false) String message,
                              Model model){
        Page<CoursesDTO>  courses = courseService.getCourses(page,size);
        model.addAttribute("courses",courses);
        model.addAttribute("message",message);

        return "courses";
    }
    @PostMapping
    public String saveCourses(@Valid @ModelAttribute("courseDto") CoursesDTO dto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if(courseService.existsByCode(dto.getCourseCode())){
            bindingResult.rejectValue("courseCode", "duplicate.courseCode","Course code must be unique");
            return "add-course";
        }

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldErrors());
            return "add-course";

        }

        courseService.saveCourse(dto);
        redirectAttributes.addAttribute("message", "Course is created successfully");
        return "redirect:/courses/list";
    }

    @GetMapping("/{id}")
    public String viewCourse(@PathVariable Long id, Model model){
        CoursesDTO course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "view-course";
    }

    @GetMapping("/{id}/edit")
    public String editCourse(@PathVariable Long id, Model model){
        CoursesDTO course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "edit-course";
    }

    @PostMapping("/{id}/update")
    public String updateCourses(@PathVariable Long id,
                              @Valid @ModelAttribute("course") CoursesDTO dto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if(courseService.existsByCodeAndIdNot(dto.getCourseCode(),id)){
            bindingResult.rejectValue("courseCode", "duplicate.courseCode","Course code must be unique");
            return "edit-course";
        }

        if(bindingResult.hasErrors()){
            return "edit-course";

        }

        courseService.updateCourse(id,dto);
        redirectAttributes.addAttribute("message", "Course is updated successfully");
        return "redirect:/courses/list";
    }
}
