package com.mjp.taskmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjp.taskmaster.entity.Project_members;
@Repository
public interface Project_memberRepo extends JpaRepository<Project_members,Long>{

}
