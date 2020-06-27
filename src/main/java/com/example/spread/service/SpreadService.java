package com.example.spread.service;

import com.example.spread.dao.ReqeustTask;
import com.example.spread.dao.SpreadDto;
import com.example.spread.domain.entity.SpreadEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpreadService {
    String saveTask(String roomId, long userId, long amount, long pplCnt);
    int pickMoney(long userId, String token);
    List<SpreadEntity> findAll();
    SpreadEntity  findByUserId(long userId);

}
