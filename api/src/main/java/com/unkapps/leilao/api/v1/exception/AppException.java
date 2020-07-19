package com.unkapps.leilao.api.v1.exception;

import com.unkapps.leilao.api.v1.exception.dto.AppError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {
    private AppError error;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public AppException(AppError error) {
        this.error = error;
    }

    public AppException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
