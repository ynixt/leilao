package com.unkapps.leilao.api.v1.dto;

import lombok.Data;

@Data
public class LoginDto {
    private final String token;
    private final String login;
}
