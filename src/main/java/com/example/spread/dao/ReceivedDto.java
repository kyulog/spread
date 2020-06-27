package com.example.spread.dao;

import com.example.spread.entity.ReceivedEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceivedDto {
    private long amount;
    private long userId;
}
