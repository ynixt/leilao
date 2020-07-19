package com.unkapps.leilao.api.exception.dto;

import lombok.Data;

import java.util.List;

@Data
public class AppError {
    public final List errors;

    public static AppError of(Code code) {
        return new AppError(List.of(new CodeError(code)));
    }
}
