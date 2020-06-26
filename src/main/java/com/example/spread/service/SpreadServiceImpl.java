package com.example.spread.service;

import com.example.spread.dao.SpreadDto;
import com.example.spread.domain.entity.SpreadEntity;
import com.example.spread.domain.repository.SpreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpreadServiceImpl implements SpreadService{
    @Autowired
    private SpreadRepository spreadRepository;

    @Override
    public List<SpreadEntity> findAll(){
        return spreadRepository.findAll();
    };

    @Override @Transactional
    public String saveTask(SpreadDto spreadDto){
        String token = setToken();
        return spreadRepository.save(spreadDto.toEntity(token)).getId();
    }

    private String setToken()
    {
        return "SSS-BBB-XXX";
    }

}
