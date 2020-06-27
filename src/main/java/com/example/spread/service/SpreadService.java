package com.example.spread.service;

import com.example.spread.dao.ResponseDto;
import com.example.spread.entity.SpreadEntity;
import org.springframework.stereotype.Service;

@Service
public interface SpreadService {
    String saveTask(String roomId, long userId, long amount, int pplCnt);
    int pickMoney(long userId, String token);
    ResponseDto findByTokenId(String token, long userId);
}
