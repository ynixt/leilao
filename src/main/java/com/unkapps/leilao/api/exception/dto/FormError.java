package com.unkapps.leilao.api.exception.dto;

import lombok.Data;

@Data
public class FormError {
    private final String attribute;

    private final String formCode;

    private final Object[] arguments;

    private final Code code = Code.FORM;
}
