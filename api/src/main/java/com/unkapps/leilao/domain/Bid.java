package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Entity
public class Bid extends DomainOneId {
    @NotNull
    @Min(0)
    @Column
    private float value;

    @Column
    @NotNull
    private ZonedDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Auction auction;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User madeByUser;
}
