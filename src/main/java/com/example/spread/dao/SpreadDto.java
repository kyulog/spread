package com.example.spread.dao;

import lombok.*;
import com.example.spread.domain.entity.SpreadEntity;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpreadDto {
    private String token;
    private int amount;
    private int pplCnt;

    public SpreadEntity toEntity(String token){
        SpreadEntity spreadEntity = SpreadEntity.builder()
                .id(token)
                .amount(amount)
                .pplCnt(pplCnt)
                .build();
        return spreadEntity;
    }
}
