package com.vishnu.studentmanagement.Repository;

import com.vishnu.studentmanagement.Entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
    Page<Students> findByActiveTrue(Pageable pageable);
    boolean existsByEmail(String email);
    List<Students> findAllByActiveTrue();

}
