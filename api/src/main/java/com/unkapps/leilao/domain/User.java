package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
