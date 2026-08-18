package com.vishnu.studentmanagement.mapper;


import com.vishnu.studentmanagement.Dto.StudentsDTO;
import com.vishnu.studentmanagement.Entity.Students;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentsDTO toDto(Students student);
    Students toEntity(StudentsDTO dto);
}
