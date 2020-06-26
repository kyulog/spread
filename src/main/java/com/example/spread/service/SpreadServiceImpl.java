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
    public void saveTask(SpreadDto spreadDto){
        spreadRepository.save(spreadDto.toEntity());
    }

}
