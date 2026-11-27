package com.obu.obu.lerning.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/students")
    public List<User> getAllStudents() {
        return userRepository.findByRole("STUDENT");
    }

    @PutMapping("/students/{id}/password")
    public ResponseEntity<?> updateStudentPassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        User student = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setPassword(request.get("password"));
        userRepository.save(student);

        return ResponseEntity.ok("Password updated successfully");
    }
}
