package com.blueprint.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blueprint.backend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
