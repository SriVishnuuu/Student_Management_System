package com.vishnu.studentmanagement.Service;

import com.vishnu.studentmanagement.Dto.EnrollmentDetailsDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentSummaryDTO;
import com.vishnu.studentmanagement.Dto.EnrollmentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {

    void enrollCourseToStudent(EnrollmentsDTO dto);

    Page<EnrollmentSummaryDTO> getEnrollmentSummary(int page, int size);

    EnrollmentDetailsDTO getEnrollmentDetails(Long id);

    List<EnrollmentSummaryDTO> getRecentEnrollments();
}
