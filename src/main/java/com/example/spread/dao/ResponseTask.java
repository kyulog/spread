package com.example.spread.dao;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseTask {
    private LocalDateTime createData;
    private long amount;
    private long usedAmount;
//    private List<Recevies> recevies;
//
//    public ResponseTask(LocalDateTime createData, long amount, long usedAmount)
//    {
//        this.createData = createData;
//        this.amount = amount;
//        this.usedAmount = usedAmount;
//    }

}

//class Recevies{
//    private long recivedMoney;
//    private long uerId;
//}
