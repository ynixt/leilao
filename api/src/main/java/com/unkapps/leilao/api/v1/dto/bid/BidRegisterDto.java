package com.unkapps.leilao.api.v1.dto.bid;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class BidRegisterDto {
    @NotNull
    @Min(0)
    private float value;

    @NotNull
    private ZonedDateTime date;

    @NotNull
    private Long auctionId;
}
