package com.obu.obu.lerning.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private CourseModuleRepository moduleRepository;

    @Autowired
    private LiveClassRepository liveClassRepository;

    @GetMapping("/live")
    public ResponseEntity<?> getActiveLiveClass(
            @RequestParam String department,
            @RequestParam String studyYear) {
        Optional<LiveClass> live = liveClassRepository
                .findFirstByDepartmentAndStudyYearAndActiveTrueOrderByIdDesc(department, studyYear);
        return live.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/modules")
    public List<CourseModule> getModulesByDeptAndYear(
            @RequestParam String department,
            @RequestParam String studyYear) {
        return moduleRepository.findByDepartmentAndStudyYear(department, studyYear);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadModule(@PathVariable Long id) throws MalformedURLException {
        CourseModule module = moduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        Path path = Paths.get(module.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + module.getFileName() + "\"")
                .body(resource);
    }
}
