package com.myProject.libraryManager.repository;

import com.myProject.libraryManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}