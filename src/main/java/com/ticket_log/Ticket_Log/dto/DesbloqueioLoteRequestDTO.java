package com.ticket_log.Ticket_Log.dto;

import lombok.Data;

import java.util.List;

@Data
public class DesbloqueioLoteRequestDTO {

    private Integer cdLote;
    private Integer cdMotivoBloqueio;
    private List<Integer> cdu;

}
