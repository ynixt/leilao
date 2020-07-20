package com.unkapps.leilao.api.v1.exception.dto;

import lombok.Data;

import java.util.List;

@Data
public class AppError {
    public final List errors;

    public static AppError of(Code code) {
        return new AppError(List.of(new CodeError(code)));
    }

    public static AppError of(Code code, String extra) {
        return new AppError(List.of(new CodeError(code, extra)));
    }
}
