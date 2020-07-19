package com.unkapps.leilao.api.v1.exception;

import com.unkapps.leilao.api.v1.exception.dto.AppError;
import com.unkapps.leilao.api.v1.exception.dto.FormError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FormError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            errors.add(new FormError(fieldName, error.getCode(), error.getArguments()));
        });

        return new ResponseEntity<>(new AppError(errors), headers, status);
    }

    @ExceptionHandler({ AppException.class })
    @ResponseBody
    public ResponseEntity<?> handleAnyException(AppException e)
    {
        return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
    }
}
