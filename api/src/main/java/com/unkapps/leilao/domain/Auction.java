package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Entity
@Data
public class Auction extends DomainOneId {
    @NotNull
    @Size(min = 2, max = 50)
    @Column
    private String name;

    @NotNull
    @Min(0)
    @Column
    private float initialValue;

    @NotNull
    @Column
    private boolean used;

    @NotNull
    @ManyToOne
    private User userResponsible;

    @NotNull
    @Column
    private ZonedDateTime openDate;

    @Column
    private ZonedDateTime endDate;
}
