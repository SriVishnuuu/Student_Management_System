package com.vishnu.studentmanagement.Dto;


import lombok.Data;

@Data
public class DashboardDTO {

    Long totalStudents;

    Long totalCourses;

    String topCourse;

    Long monthlyEnrollments;
}
