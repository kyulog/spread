package com.example.spread.service;

import com.example.spread.dao.ResponseDto;
import com.example.spread.entity.SpreadEntity;
import org.springframework.stereotype.Service;

@Service
public interface SpreadService {
    String saveTask(String roomId, long userId, long amount, long pplCnt);
    int pickMoney(long userId, String token);
    SpreadEntity  findByUserId(long userId);
    ResponseDto findByTokenId(String token, long userId);
}
