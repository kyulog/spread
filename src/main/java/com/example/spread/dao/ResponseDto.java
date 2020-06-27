package com.example.spread.dao;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDto {

    private LocalDateTime createData;
    private long amount;
    private long usedAmount;
    private List<ReceivedDto> receives;

    public ResponseDto(long amount, long usedAmount, LocalDateTime createData) {
        this.amount = amount;
        this.usedAmount = usedAmount;
        this.createData = createData;
    }

//    public ResponseDto(long userId, long predictedMoney) {
//        System.out.println("recevied=======================");
//        receives = new ArrayList<>();
//        receives.get(0).setUserId(userId);
//        receives.get(0).setReceivedMoney(predictedMoney);
//        this.receives.add((ReceivedDto) receives);
//    }
}
