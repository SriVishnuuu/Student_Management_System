package com.vishnu.studentmanagement.Dto;


import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Entity.Students;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EnrollmentsDTO {

    @NotNull(message = "Student is required")
    private Long studentId;

    @NotEmpty(message = "Please select at least one course")
    private List<Long> courseIds = new ArrayList<>();

    private LocalDate enrolledAt;

}
