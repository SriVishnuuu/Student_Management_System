package com.vishnu.studentmanagement.Repository;


import com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO;
import com.vishnu.studentmanagement.Entity.Enrollments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {
    boolean existsByStudentIdAndCourseId(Long StudentId, Long CourseId);
    List<Enrollments> findByStudentId(Long StudentId);


    @Query("""
        SELECT new com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO(
                s.id,
                CONCAT(s.firstName,' ',s.lastName),
                s.email,
                COUNT(e),
                COALESCE(SUM(c.fee),0)
        )
        FROM Enrollments e
        JOIN e.student s
        JOIN e.course c
        GROUP BY s.id,s.firstName,s.lastName,s.email
        """)
    Page<EnrollmentSummaryDTO> findEnrollmentSummary(Pageable pageable);


    @Query("""
        SELECT new com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO(
                s.id,
                CONCAT(s.firstName,' ',s.lastName),
                s.email,
                COUNT(e),
                COALESCE(SUM(c.fee),0)
        )
        FROM Enrollments e
        JOIN e.student s
        JOIN e.course c
        GROUP BY s.id,s.firstName,s.lastName,s.email
        ORDER BY MAX(e.id) DESC
        """)
    Page<EnrollmentSummaryDTO> findRecentEnrollments(Pageable pageable);


    @Query("""
        SELECT e
        FROM Enrollments e
        JOIN FETCH e.student
        JOIN FETCH e.course
        WHERE e.student.id = :studentId
        """)
    List<Enrollments> findEnrollmentsByStudentId(Long studentId);

    @Query("""
    SELECT COUNT(e)
    FROM Enrollments e
    WHERE MONTH(e.enrolledAt) = MONTH(CURRENT_DATE)
      AND YEAR(e.enrolledAt) = YEAR(CURRENT_DATE)
    """)
    Long countEnrollmentsThisMonth();
}

