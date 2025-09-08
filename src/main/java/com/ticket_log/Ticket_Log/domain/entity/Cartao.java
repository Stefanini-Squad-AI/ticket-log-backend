package com.ticket_log.Ticket_Log.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "TB_CARTAO")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DT_GERACAO", updatable = false, nullable = false)
    private LocalDateTime dataGeracao;

    @Column(name = "NR_CARTAO", nullable = false)
    private Long nrCartao;

    @Column(name = "NR_VIA_CARTAO", nullable = false)
    private Integer nrViaCartao;

    @Column(name="IDENTIFICACAO", nullable = false)
    private String identificacao;

    @Column(name="STATUS", nullable = false)
    private String status;

}