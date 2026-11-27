package com.obu.obu.lerning.system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {
    List<CourseModule> findByDepartmentAndStudyYear(String department, String studyYear);
}
