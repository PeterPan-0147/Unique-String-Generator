package com.edrone.stringgenerator.repository;

import com.edrone.stringgenerator.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
        List<Task> findTasksByCheckedFalse();
}
