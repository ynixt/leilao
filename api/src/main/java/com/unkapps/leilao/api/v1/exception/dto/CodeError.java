package com.unkapps.leilao.api.v1.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CodeError {
    private final Code code;
    public String extra;
}

