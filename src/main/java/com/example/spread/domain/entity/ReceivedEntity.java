package com.example.spread.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "received")
@Getter @Setter
public class ReceivedEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "received_id")
    private int id;

//    @Id @Column(name = "spread_token_id")
//    private String spreadTokenId;

    private int userId;
    @Column(nullable = false)
    private int predictedMoney;

//    @ManyToOne
//    @JoinColumn(name ="spread_token_id", updatable = false, insertable = false)
//    private SpreadEntity spreadEntity;
}
