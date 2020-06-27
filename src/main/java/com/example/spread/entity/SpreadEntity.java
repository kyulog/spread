package com.example.spread.entity;

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
//@IdClass(SpreadEntityId.class)
@Builder
public class SpreadEntity extends TimeEntity {
    @Id @Column(name = "spread_id")
    private String id;
    private long amount;
    private long pplCnt;
    private long usedAmount;
    private String roomId;
    private long userId;

//    @OneToMany
//    @JoinColumn(name = "id")
//    private List<ReceivedEntity> receivedEntities = new ArrayList<ReceivedEntity>();

    public SpreadEntity(String token, String roomId, long userId, long amount, long pplCnt) {
        super();
        this.id = token;
        this.roomId = roomId;
        this.userId = userId;
        this.amount = amount;
        this.pplCnt = pplCnt;
    }
//    @OneToMany(targetEntity = ReceivedEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="spread_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReceivedEntity> receivedEntities;

    public boolean addReceivedEntity(ReceivedEntity receivedEntity)
    {
        if(receivedEntities == null)
            receivedEntities = new ArrayList<>();
        return this.receivedEntities.add(receivedEntity);
    }
}
