package com.github.juliherms.ticketservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TicketDTO  implements Serializable {

    private static final long serialVersionUID = 639741717645810500L;

    private String ticketId;
    private Date expirteDate;
    private String status;
}