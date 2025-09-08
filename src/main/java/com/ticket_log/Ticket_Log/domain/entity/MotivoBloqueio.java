package com.ticket_log.Ticket_Log.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MOTIVO_BLOQUEIO")
@Getter
@Setter
public class MotivoBloqueio {
    @Id
    @Column(name = "motivoBloqueioId")
    private Integer id;

    @Column(name = "dsMotivoBloqueio")
    private String descricao;

}
