package com.obu.obu.lerning.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private LiveClassRepository liveClassRepository;

    @PostMapping("/upload-module")
    public ResponseEntity<?> uploadModule(
            @RequestParam("title") String title,
            @RequestParam("department") String department,
            @RequestParam("studyYear") String studyYear,
            @RequestParam("file") MultipartFile file) throws IOException {

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        CourseModule module = new CourseModule();
        module.setTitle(title);
        module.setDepartment(department);
        module.setStudyYear(studyYear);
        module.setFileName(fileName);
        module.setFilePath(filePath.toString());

        return ResponseEntity.ok(moduleRepository.save(module));
    }

    @PostMapping("/start-live")
    public ResponseEntity<?> startLiveClass(@RequestBody LiveClass liveClass) {
        liveClass.setActive(true);
        return ResponseEntity.ok(liveClassRepository.save(liveClass));
    }
}
