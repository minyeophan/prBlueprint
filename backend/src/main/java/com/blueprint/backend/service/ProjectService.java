package com.blueprint.backend.service;

import com.blueprint.backend.entity.Project;
import com.blueprint.backend.repository.ProjectRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public Project getProjectById(Long id){
        return projectRepository.findById(id).orElse(null);
    }
    public Project createProject(Project project){
        return projectRepository.save(project);
    }
    public Project updateProject(Project project){
        return projectRepository.save(project);
    }
    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }
}
