package com.example.spread.domain.repository;
import com.example.spread.domain.entity.SpreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpreadRepository extends JpaRepository<SpreadEntity, Integer>{
    // No need implementation, Just one interface, and you have CRUD,
}
