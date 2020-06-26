package com.example.spread.service;

import com.example.spread.dao.SpreadDto;
import com.example.spread.domain.entity.SpreadEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpreadService {
    void saveTask(SpreadDto spreadDto);
    List<SpreadEntity> findAll();
}
