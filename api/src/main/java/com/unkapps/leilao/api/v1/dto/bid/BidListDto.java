package com.unkapps.leilao.api.v1.dto.bid;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BidListDto {
    private Long id;

    private float value;

    private ZonedDateTime date;

    private UserDto madeByUser;

    @Data
    public static class UserDto {
        private final Long id;
        private final String login;
    }
}
