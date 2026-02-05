package com.blueprint.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blueprint.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
