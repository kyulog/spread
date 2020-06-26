package com.example.spread.domain.entity;

import com.example.spread.idclass.SpreadEntityId;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Id;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity @Table(name = "spread")
@IdClass(SpreadEntityId.class)
@Builder
public class SpreadEntity extends TimeEntity {
    @Id @Column(name = "spread_token_id")
    private String spreadTokenId;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private int pplCnt;

    private String roomNum;
    private boolean done;

//    @OneToMany(mappedBy = "spreadEntity", fetch = FetchType.LAZY)
    @OneToMany
    @JoinColumn(name = "spread_token_id")
    private List<RecivedEntity> recivedEntities = new ArrayList<RecivedEntity>();

}
