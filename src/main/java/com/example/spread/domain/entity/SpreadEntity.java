package com.example.spread.domain.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity @Table(name = "spread")
@Builder
public class SpreadEntity extends TimeEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "spread_token")
    private int token;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private int pplCnt;
    private boolean done;

//    @OneToMany @JoinColumn(name = "order")
//    private List<RecivedEntity> recivedEntityLisies = new ArrayList<RecivedEntity>();

}
