package com.example.spread.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDto {
    private long amount;
    private int pplCnt;
}
