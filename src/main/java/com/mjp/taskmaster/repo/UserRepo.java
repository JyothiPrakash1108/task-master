package com.mjp.taskmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjp.taskmaster.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
