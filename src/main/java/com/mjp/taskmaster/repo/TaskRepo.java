package com.mjp.taskmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjp.taskmaster.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{

}
