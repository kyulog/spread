package com.example.spread.repository;
import com.example.spread.entity.ReceivedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedRepository extends JpaRepository<ReceivedEntity, Integer>  {
}
