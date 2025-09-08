package com.ticket_log.Ticket_Log.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaoDesbloqueadoDTO {
    private Long nrCartao;
    private String identificadorCartao;

}
