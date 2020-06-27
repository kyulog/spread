package com.example.spread.domain.repository;
import com.example.spread.domain.entity.SpreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpreadRepository extends JpaRepository<SpreadEntity, String>{
    // No need implementation, Just one interface, and you have CRUD,

    Optional<SpreadEntity> findById(String token);
//    @Query("SELECT new com.javatechie.jpa.dto.RequestTask()")
    SpreadEntity findByUserId(long userId);

}
