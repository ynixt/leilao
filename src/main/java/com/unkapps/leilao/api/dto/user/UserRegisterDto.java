package com.unkapps.leilao.api.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserRegisterDto implements Serializable {
    @NotNull
    @Size(min = 3, max = 15)
    private String login;

    @NotNull
    @Size(min = 3, max = 15)
    private String password;
}
