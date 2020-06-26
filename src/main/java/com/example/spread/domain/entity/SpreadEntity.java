package com.example.spread.domain.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity @Table(name = "spread")
@Builder
public class SpreadEntity extends TimeEntity {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private int pplCnt;

    private boolean done;
    private String token;

//    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL) //(1)
//    @JoinColumn
//    private Collection<RecivedInfoEntity> recivedInfoEntities;

//    @Builder
//    public SpreadEntity(int id, int amount, int pplCnt){
//        this.id = id;
//        this.amount = amount;
//        this.pplCnt = pplCnt;
//    }
}
