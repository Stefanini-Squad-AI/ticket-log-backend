package com.ticket_log.Ticket_Log.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lote")
@Data
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidadeCartoes;

    private Integer cancelados;
    private Integer bloqueados;
    private Integer desbloqueados;

    @ManyToOne
    @JoinColumn(name = "cd_motivo_bloqueio")
    private MotivoBloqueio motivoBloqueio;

    @Column(length = 20)
    private String statusEntrega;

    private LocalDate embossingDate;

    @OneToMany(mappedBy = "nrViaCartao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes;

}
