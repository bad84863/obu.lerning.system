package com.obu.obu.lerning.system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LiveClassRepository extends JpaRepository<LiveClass, Long> {
    Optional<LiveClass> findFirstByDepartmentAndStudyYearAndActiveTrueOrderByIdDesc(String department, String studyYear);
}
