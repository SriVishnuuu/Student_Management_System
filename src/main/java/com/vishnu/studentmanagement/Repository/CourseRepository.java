package com.vishnu.studentmanagement.Repository;

import com.vishnu.studentmanagement.Entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
    boolean existsByCourseCodeIgnoreCase(String courseCode);
    Page<Courses> findByActiveTrue(Pageable pageable);
    boolean existsByCourseCodeIgnoreCaseAndIdNot(String courseCode , Long id);
    List<Courses> findAllByActiveTrue();

}
