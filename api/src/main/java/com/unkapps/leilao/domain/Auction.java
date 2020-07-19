package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private User userResponsible;

    @Column(name = "user_responsible_id", updatable = false, insertable = false)
    private Long userResponsibleId;

    @NotNull
    @Column
    private ZonedDateTime openDate;

    @Column
    private ZonedDateTime endDate;

    public String toString() {
        return "id" + ", " + name;
    }

    public boolean isFinished() {
        return this.endDate != null;
    }
}
