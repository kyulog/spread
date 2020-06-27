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
    private final List<ReceivedDto> receives = new ArrayList<>();

    public ResponseDto(long amount, long usedAmount, LocalDateTime createData) {
        this.amount = amount;
        this.usedAmount = usedAmount;
        this.createData = createData;
    }

    public void addReceived(ReceivedDto rd) {
        receives.add(rd);
    }
}
