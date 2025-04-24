package rs.ac.singidnum.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getTicketByIdWithFlight(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTicket(@RequestBody Ticket model) {
        service.createTicket(model);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTicket(@PathVariable Integer id, @RequestBody Ticket model) {
        service.updateTicket(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Integer id) {
        service.deleteTicket(id);
    }
}
