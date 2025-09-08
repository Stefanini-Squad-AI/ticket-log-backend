package com.ticket_log.Ticket_Log.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CartaoDTO {

    private Long clienteId;
    private Long loteId;
    private Long motivoBloqueioId;
    private Long tipoCartaoId;

    public CartaoDTO(Long clienteId, Long loteId, Long motivoBloqueioId, Long tipoCartaoId) {
        this.clienteId = clienteId;
        this.loteId = loteId;
        this.motivoBloqueioId = motivoBloqueioId;
        this.tipoCartaoId = tipoCartaoId;
    }

}
