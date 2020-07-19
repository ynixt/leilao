package com.unkapps.leilao.api.v1.dto.auction;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AuctionSingleDto {
    private Long id;

    private String name;

    private float initialValue;

    private boolean used;

    private UserDto userResponsible;

    private ZonedDateTime openDate;

    private ZonedDateTime endDate;

    @Data
    public static class UserDto {
        private final Long id;
        private final String login;
    }
}
