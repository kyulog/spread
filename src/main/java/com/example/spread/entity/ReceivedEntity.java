package com.example.spread.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "received")
@Getter @Setter
public class ReceivedEntity extends TimeEntity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private long userId;
    private long predictedMoney;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="spread_id", updatable = false, insertable = false)
    private SpreadEntity spreadEntity;

    public ReceivedEntity(long amount){
        this.predictedMoney = amount;
    }
}
