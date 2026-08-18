package com.vishnu.studentmanagement.mapper;


import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Courses toEntity(CoursesDTO dto);
    CoursesDTO toDto(Courses course);
}
