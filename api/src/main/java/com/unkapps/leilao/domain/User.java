package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User extends DomainOneId {
    @Column
    @NotBlank
    private String login;

    @Column
    @NotBlank
    private String password;

    @Column
    @NotNull
    private boolean active;
}
