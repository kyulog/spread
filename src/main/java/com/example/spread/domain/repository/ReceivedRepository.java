package com.example.spread.domain.repository;
import com.example.spread.domain.entity.ReceivedEntity;
import com.example.spread.domain.entity.SpreadEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ReceivedRepository extends JpaRepository<ReceivedEntity, Integer>  {
    //    SpreadEntity<ReceivedEntity> findBySpreadEntityId(String id, )
//    List<SpreadEntity> findBySpreadEntity(SpreadEntity spreadEntity, Sort sort);

}
