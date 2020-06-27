package com.example.spread.repository;
import com.example.spread.entity.SpreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpreadRepository extends JpaRepository<SpreadEntity, String>{
    // No need implementation, Just one interface, and you have CRUD,
    Optional<SpreadEntity> findById(String token);
    SpreadEntity findByUserId(long userId);

}
