package com.ticket_log.Ticket_Log.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record NovoLoteCartaoRequest(
        @NotNull List<ItemCartao> cartoes
) {
    public record ItemCartao(
            @NotNull Long nrCartao,
            @NotNull Long nrViaCartao,
            @NotNull Long cdBloqueio,
            @NotNull LocalDateTime dtBloqueio,
            LocalDateTime dtDesbloqueio
    ) {}
}
