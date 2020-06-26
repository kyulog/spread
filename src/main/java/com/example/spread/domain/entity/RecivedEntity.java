package com.example.spread.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//@Table(catalog = "recived")
//@Table
@Getter @Setter
public class RecivedEntity extends TimeEntity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int order;

    private int userId;
    private int predictedMoney;

//    @ManyToOne
//    @JoinColumn(name = "spread_token", insertable = false, updatable = false)
//    SpreadEntity spreadEntity;
}
