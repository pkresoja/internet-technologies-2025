package rs.ac.singidnum.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidnum.ticket.entity.Ticket;
import rs.ac.singidnum.ticket.service.TicketService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ticket")
@CrossOrigin
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @GetMapping
    public List<Ticket> getTickets() {
        return service.getTickets();
    }
}
