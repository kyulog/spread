package com.example.spread.dao;

import com.example.spread.entity.ReceivedEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceivedDto {
    // 받은 금액
    long amount;
    long userId;

    // 받은 사용자 아이디
}
