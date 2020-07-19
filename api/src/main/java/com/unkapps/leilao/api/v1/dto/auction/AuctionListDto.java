package com.unkapps.leilao.api.v1.dto.auction;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AuctionListDto {
    private Long id;

    private String name;

    private float initialValue;

    private boolean used;

    private UserDto userResponsible;

    private boolean finished;

    @Data
    public static class UserDto {
        private final Long id;
        private final String login;
    }
}
