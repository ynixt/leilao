package com.unkapps.leilao.api.exception;

import com.unkapps.leilao.api.exception.dto.AppError;
import lombok.Data;

@Data
public class AppException extends RuntimeException{
    private final AppError error;
}
