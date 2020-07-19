package com.unkapps.leilao.api.v1.exception.dto;

import lombok.Data;

@Data
public class FormError {
    private final String attribute;

    private final String formCode;

    private final Object[] arguments;

    private final Code code = Code.FORM;
}
