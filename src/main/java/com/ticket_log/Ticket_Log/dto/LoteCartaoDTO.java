package com.ticket_log.Ticket_Log.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoteCartaoDTO {
    private Integer cdLoteEmbosse;
    private Integer nrQuantidadeCartao;
    private Integer nrCartUsuCanc;
    private Integer nrBloqueados;
    private Integer nrDesbloqueados;
    private String dsMotivoBloqueio;
    private String statusEntrega;
    private LocalDateTime dtCadastro;

}
