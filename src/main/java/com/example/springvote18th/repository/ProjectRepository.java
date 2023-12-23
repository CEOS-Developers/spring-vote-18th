package com.example.springvote18th.repository;

import com.example.springvote18th.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByCountDesc();
}
