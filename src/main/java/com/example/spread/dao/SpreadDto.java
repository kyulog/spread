package com.example.spread.dao;

import lombok.*;
import com.example.spread.domain.entity.SpreadEntity;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpreadDto {
//    private int id;
    private int amount;
    private int pplCnt;

    public SpreadEntity toEntity(){
        SpreadEntity spreadEntity = SpreadEntity.builder()
//                .id(id)
                .amount(amount)
                .pplCnt(pplCnt)
                .build();
        return spreadEntity;
    }
}
