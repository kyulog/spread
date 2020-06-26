package com.example.spread.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "recived")
@Getter @Setter
public class RecivedEntity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "recived_id")
    private int id;

//    @Id @Column(name = "spread_token_id")
//    private String spreadTokenId;

    private int userId;
    @Column(nullable = false)
    private int predictedMoney;

//    @ManyToOne//(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name ="spread_token_id")//, nullable = false, updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    @JoinColumn(name = "spread_token_id", insertable = false, updatable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    @JoinColumn(name ="spread_token_id")
    private SpreadEntity spreadEntity;
}
