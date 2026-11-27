package com.obu.obu.lerning.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmailAndPassword("admin@obu.edu", "admin123").isEmpty()) {
                User admin = new User();
                admin.setFullName("System Admin");
                admin.setEmail("admin@obu.edu");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (userRepository.findByEmailAndPassword("teacher@obu.edu", "teacher123").isEmpty()) {
                User instructor = new User();
                instructor.setFullName("Course Instructor");
                instructor.setEmail("teacher@obu.edu");
                instructor.setPassword("teacher123");
                instructor.setRole("INSTRUCTOR");
                userRepository.save(instructor);
            }
        };
    }
}
