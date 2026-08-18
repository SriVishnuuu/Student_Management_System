package com.vishnu.studentmanagement.Controller;


import com.vishnu.studentmanagement.Service.DashboardService;
import com.vishnu.studentmanagement.ServiceImpl.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private EnrollmentServiceImpl enrollmentService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String login(Model model){
        model.addAttribute("dashboard", dashboardService.getStudentManagementData());
        model.addAttribute("enrollments",enrollmentService.getRecentEnrollments());
        return "dashboard";
    }
}
