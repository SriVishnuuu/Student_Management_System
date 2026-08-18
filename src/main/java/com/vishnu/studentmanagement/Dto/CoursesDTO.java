package com.vishnu.studentmanagement.Dto;


import com.vishnu.studentmanagement.Entity.Enrollments;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CoursesDTO {

    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(max = 150, message = "Max of 150 characters are allowed")
    private String courseName;

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Duration is required")
    private String duration;

    private boolean active=true;

    @NotNull(message = "fee is required")
    @PositiveOrZero(message = "fee must be either 0 or above")
    private BigDecimal fee;

    @Size(max = 500, message = "Max of 500 characters are allowed")
    private String description;

}
