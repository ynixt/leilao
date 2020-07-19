package com.unkapps.leilao.api.v1.exception;

import com.unkapps.leilao.api.v1.exception.dto.AppError;
import lombok.Data;

@Data
public class AppException extends RuntimeException{
    private final AppError error;
}
