package com.example.spread.service;

import com.example.spread.dao.ReqeustTask;
import com.example.spread.dao.SpreadDto;
import com.example.spread.domain.entity.ReceivedEntity;
import com.example.spread.domain.entity.SpreadEntity;
import com.example.spread.domain.repository.ReceivedRepository;
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
    @Autowired
    private ReceivedRepository receivedRepository;

    @Override
    public List<SpreadEntity> findAll(){
        return spreadRepository.findAll();
    };

    @Override //@Transactional
    public String saveTask(String roomId, long userId, long amount, long pplCnt){
        String token = setToken();
   
        SpreadEntity spreadEntity = new SpreadEntity(token, roomId, userId, amount, pplCnt, false);
        ReceivedEntity receivedEntity = null;
        for(int i = 0 ; i < 3; i++)
        {
            receivedEntity = new ReceivedEntity(amount+1);
            spreadEntity.addReceivedEntity(receivedEntity);

        }
        
        receivedEntity.setSpreadEntity(spreadEntity);
        spreadRepository.save(spreadEntity);

//        receivedRepository.save(receivedEntity);

        return spreadEntity.getId();
    }


    private String setToken()
    {
        return "SSS-BBB-XXX".toString();
    }

}
