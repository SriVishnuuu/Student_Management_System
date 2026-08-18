package com.vishnu.studentmanagement.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class EnrollmentDetailsDTO {

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long totalCourses;
    private BigDecimal totalFee;

    private List<CoursesDTO> courses = new ArrayList<>();
}
