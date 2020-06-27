package com.example.spread.service;

import com.example.spread.dao.ReceivedDto;
import com.example.spread.dao.ResponseDto;
import com.example.spread.entity.ReceivedEntity;
import com.example.spread.entity.SpreadEntity;
import com.example.spread.repository.ReceivedRepository;
import com.example.spread.repository.SpreadRepository;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpreadServiceImpl implements SpreadService{
    @Autowired
    private SpreadRepository spreadRepository;
    @Autowired
    private ReceivedRepository receivedRepository;

    @Override @Transactional
    public ResponseDto findByTokenId(String token, long userId) {
        System.out.print("ssssss");
        Optional<SpreadEntity> spreadEntity = spreadRepository.findById(token);
        ResponseDto responseDto = null;

        return spreadEntity.map(it -> {
            ResponseDto dto = new ResponseDto(it.getAmount(), it.getUsedAmount(), it.getCreateData());
            it.getReceivedEntities().stream().filter(r -> r.getUserId() != 0).forEach(r -> {
                ReceivedDto rd = new ReceivedDto(r.getPredictedMoney(), r.getUserId());
                dto.addReceived(rd);
            });
            return dto;
        }).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override @Transactional
    public String saveTask(String roomId, long userId, long amount, int pplCnt){
        SpreadEntity spreadEntity = new SpreadEntity(generateToken(), roomId, userId, amount, pplCnt);
        ReceivedEntity receivedEntity = null;
        long[] div = divide(amount, pplCnt);
        for(int i = 0 ; i < div.length; i++)
        {
            receivedEntity = new ReceivedEntity(div[i]);
            spreadEntity.addReceivedEntity(receivedEntity);
        }
        receivedEntity.setSpreadEntity(spreadEntity);
        spreadRepository.save(spreadEntity);

        return spreadEntity.getId();
    }

    private long[] divide(long amount, int pplCnt)
    {
        long[] result = new long[pplCnt];
        long div;
        long re = amount;
        for(int i = 0; i < pplCnt-1; i++)
        {
            div = re / (pplCnt + i / pplCnt);
            re -= div;
            result[i] = div;
        }
        result[pplCnt-1] = re;

        return result;
    }

    private String generateToken()
    {
        String token = "";
        try {
            java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
            byte rndBytes[]  = new byte[3];

            do{
                random.nextBytes(rndBytes);
                token = Base64.getEncoder().encodeToString(rndBytes).substring(0,3);
            }while (!spreadRepository.findById(token).isEmpty());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }

    private boolean timeValidate(LocalDateTime requestTime, LocalDateTime createTime)
    {
        boolean result = true;
        //Checking the task is created within 10 min.
        if(requestTime.toLocalDate().equals(createTime.toLocalDate()) &&
            createTime.toLocalTime().getMinute() - requestTime.toLocalTime().getMinute() <= 10);
        else{
            result = false;
        }

        return result;
    }
    @Transactional
    public int pickMoney(long userId, String token) {
        int result=0;

        if(!spreadRepository.findById(token).isEmpty()) {
            //Checking the user is created user or not.
            if (spreadRepository.findById(token).get().getUserId() == userId)
                result= 1;
            if(timeValidate(LocalDateTime.now(), spreadRepository.findById(token).get().getCreateData())){
                    Optional<SpreadEntity> sp = spreadRepository.findById(token);

                    //Should be added the validator to request user are same room with created user
                    //But, The token is considering the user is including this room.
                    for(int i = 0 ; i < sp.get().getReceivedEntities().size(); i++) {
                        if (sp.get().getReceivedEntities().get(i).getUserId() == userId) {
                            result = 2;
                            break;
                        }
                        if (sp.get().getReceivedEntities().get(i).getUserId() == 0) {
                            sp.get().setUsedAmount(sp.get().getUsedAmount() + sp.get().getReceivedEntities().get(i).getPredictedMoney());
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



}
