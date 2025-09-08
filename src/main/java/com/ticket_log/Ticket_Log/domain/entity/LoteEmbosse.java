package com.ticket_log.Ticket_Log.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOTE_EMBOSSE")
@Data
public class LoteEmbosse {
    @Id
    @Column(name = "cd_lote_embosse")
    private Integer id;

    @Column(name = "dt_finalizacao")
    private LocalDateTime dataFinalizacao;

}
