package com.vishnu.studentmanagement.Config;


import com.vishnu.studentmanagement.Entity.Users;
import com.vishnu.studentmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataIntializer {

    @Bean
    CommandLineRunner loadSampleData(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     @Value("${app.admin.username}") String adminUsername,
                                     @Value("${app.admin.password}") String adminPassword){

        return args -> {
            if(!userRepository.existsByUsername("Admin")) {
                Users user = new Users();
                user.setUsername(adminUsername);
                user.setPassword(passwordEncoder.encode(adminPassword));
                user.setActive(true);
                userRepository.save(user);
            }
        };
    }
}
