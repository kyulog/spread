package com.example.spread.controller;

import com.example.spread.dao.RequestDto;
import com.example.spread.dao.RequestReceiveDto;
import com.example.spread.entity.ReceivedEntity;
import com.example.spread.entity.SpreadEntity;

import com.example.spread.repository.SpreadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SpreadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getSpreadTask() throws Exception{
        String token = "BBB";
        long userId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/spread")
                .header("TOKEN", token)
                .header("X-USER-ID", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void pickMoney() throws Exception{
        String roomId = "root";
        long userId = 1234;
        String token = "B";

        RequestDto requestDto = new RequestDto();
        requestDto.setAmount(100000);
        requestDto.setPplCnt(10);

        SpreadEntity spreadEntity = new SpreadEntity(token, roomId, userId, requestDto.getAmount(), requestDto.getPplCnt());

        RequestReceiveDto requestReceiveDto = new RequestReceiveDto();
        requestReceiveDto.setToken(token);

        SpreadRepository spreadRepository = null;

        Optional<SpreadEntity> getSpreadEntity = spreadRepository.findById(token);


        ReceivedEntity receivedEntity = new ReceivedEntity(requestDto.getAmount());
        getSpreadEntity.get().addReceivedEntity(receivedEntity);



        mockMvc.perform( MockMvcRequestBuilders
                .post("/receive")
                .content(asJsonString(spreadRepository))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void createNormalCase() throws Exception{
        String roomId = "root";
        long userId = 1234;
        String token = "AAA";

        RequestDto requestDto = new RequestDto();
        requestDto.setAmount(100000);
        requestDto.setPplCnt(10);

        SpreadEntity spreadEntity = new SpreadEntity(token, roomId, userId, requestDto.getAmount(), requestDto.getPplCnt());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/spread")
                .content(asJsonString(spreadEntity))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    public static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}