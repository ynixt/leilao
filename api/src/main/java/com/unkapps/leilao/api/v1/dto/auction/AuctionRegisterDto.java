package com.unkapps.leilao.api.v1.dto.auction;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Data
public class AuctionRegisterDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    private float initialValue;

    @NotNull
    private boolean used;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime openDate;
}
