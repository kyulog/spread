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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
    }

    @Override
    public SpreadEntity  findByUserId(long userId) {
        return spreadRepository.findByUserId(userId);
    }

    @Override @Transactional
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

        return spreadEntity.getId();
    }


    @Transactional
    public int pickMoney(long userId, String token) {
        int result=0;
        if(!spreadRepository.findById(token).isEmpty()) {
            //Checking the user is created user or not.
            if (spreadRepository.findById(token).get().getUserId() == userId)
                result= 1;

            //Checking the task is created with 10 min.
            LocalDateTime nowTime = LocalDateTime.now();
            if (nowTime.toLocalDate().equals(spreadRepository.findById(token).get().getCreateData().toLocalDate())
                    && spreadRepository.findById(token).get().getCreateData().toLocalTime().getMinute() - nowTime.toLocalTime().getMinute() <= 10) {
                    Optional<SpreadEntity> sp = spreadRepository.findById(token);

                    //Should be added the validator to request user are same room with created user
                    for(int i = 0 ; i < sp.get().getReceivedEntities().size(); i++) {
                        if (sp.get().getReceivedEntities().get(i).getUserId() == userId) {
                            result = 2;
                            break;
                        }
                        if (sp.get().getReceivedEntities().get(i).getUserId() == 0) {
                            sp.get().getReceivedEntities().get(i).setUserId(userId);
                            result= 5;
                            break;
                        }
                        else{
                            result = 4;
                        }
                    }
            }
            else{
                result= 3;
            }
        }
        return result;
    }

    private String setToken()
    {
        return "SSS-BBB-XXX".toString();
    }

}
