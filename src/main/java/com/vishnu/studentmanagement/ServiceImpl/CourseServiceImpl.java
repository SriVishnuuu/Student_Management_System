package com.vishnu.studentmanagement.ServiceImpl;

import com.vishnu.studentmanagement.Dto.CoursesDTO;
import com.vishnu.studentmanagement.Entity.Courses;
import com.vishnu.studentmanagement.Repository.CourseRepository;
import com.vishnu.studentmanagement.Service.CourseService;
import com.vishnu.studentmanagement.mapper.CourseMapper;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper mapper;

    @Override
    public CoursesDTO saveCourse(CoursesDTO courseDto) {

        Courses course = mapper.toEntity(courseDto);

        courseRepository.save(course);

        return mapper.toDto(course);
    }

    @Override
    public boolean existsByCode(String code) {
        return courseRepository.existsByCourseCodeIgnoreCase(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, Long id) {
        return courseRepository.existsByCourseCodeIgnoreCaseAndIdNot(code,id);
    }

    @Override
    public Page<CoursesDTO> getCourses(int page, int size) {

        PageRequest pageRequest  = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id"));

        return courseRepository.findByActiveTrue(pageRequest).map(course -> mapper.toDto(course));
    }

    @Override
    public CoursesDTO getCourseById(Long id) {
        Courses course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("No course found"));
        return mapper.toDto(course);
    }

    @Override
    public CoursesDTO updateCourse(Long id, CoursesDTO courseDTO) {
        Courses course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("No course found"));


        course.setCourseName(courseDTO.getCourseName());
        course.setCourseCode(courseDTO.getCourseCode());
        course.setFee(courseDTO.getFee());
        course.setDuration(courseDTO.getDuration());
        course.setDescription(courseDTO.getDescription());
        course.setActive(courseDTO.isActive());

        Courses updated = courseRepository.save(course);

        return mapper.toDto(updated);

    }

    @Override
    public List<CoursesDTO> getAllCourses() {
        return courseRepository.findAllByActiveTrue().stream().map(course -> mapper.toDto(course)).toList();
    }
}
