package com.example.springsocial.repository.database;

import com.example.springsocial.entity.database.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<DbUser, Long> {
    boolean existsByName(String name);
}
