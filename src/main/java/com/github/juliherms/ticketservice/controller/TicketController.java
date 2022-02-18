package com.github.juliherms.ticketservice.controller;


import com.github.juliherms.ticketservice.dto.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {

    @Autowired
    private WebClient paymentWebClient;

    private static final Logger logger = LogManager.getLogger(TicketController.class);

    @GetMapping("/{ticket-id}")
    public Mono<TicketDTO> getTransactionByTransactionId(@PathVariable("ticket-id") String ticketId) {
        return paymentWebClient.get()
                .uri("/" + ticketId).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(TicketDTO.class)
                .doOnSubscribe(
                        subscription -> logger.info("Client Request transaction [{}]", ticketId)
                )
                .doOnSuccess(
                        response -> logger.info("Client Response transaction [{}]", response)
                );
    }
}
