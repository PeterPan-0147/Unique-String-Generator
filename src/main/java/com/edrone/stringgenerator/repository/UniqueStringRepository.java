package com.edrone.stringgenerator.repository;

import com.edrone.stringgenerator.model.Task;
import com.edrone.stringgenerator.model.UniqueString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
@Repository

public interface UniqueStringRepository extends JpaRepository<UniqueString, Long> {
    Set<UniqueString> findByTask(Optional<Task> task);
}
