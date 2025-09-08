package com.ticket_log.Ticket_Log.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoCartao {

    @Id
    @Column(name = "tipoCartaoId")
    private Long id;

    private String descricao;
}