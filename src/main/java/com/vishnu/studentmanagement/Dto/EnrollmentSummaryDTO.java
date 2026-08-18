package com.vishnu.studentmanagement.Dto;

import java.math.BigDecimal;

public record EnrollmentSummaryDTO(
        Long studentId,
        String studentName,
        String studentEmail,
        Long totalCourses,
        BigDecimal totalFee
) {}
