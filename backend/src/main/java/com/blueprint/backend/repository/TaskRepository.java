package com.blueprint.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blueprint.backend.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
