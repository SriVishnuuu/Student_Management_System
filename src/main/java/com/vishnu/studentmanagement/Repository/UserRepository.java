package com.vishnu.studentmanagement.Repository;

import com.vishnu.studentmanagement.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    boolean existsByUsername(String name);

    Optional<Users> findByUsername(String name);
}
