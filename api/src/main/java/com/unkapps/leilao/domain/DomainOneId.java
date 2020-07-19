package com.unkapps.leilao.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class DomainOneId extends Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
