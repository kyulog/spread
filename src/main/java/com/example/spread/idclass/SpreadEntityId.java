package com.example.spread.idclass;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SpreadEntityId implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    private String spreadTokenId;
}
