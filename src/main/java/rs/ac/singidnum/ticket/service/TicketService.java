package rs.ac.singidnum.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidnum.ticket.entity.Ticket;
import rs.ac.singidnum.ticket.model.FlightModel;
import rs.ac.singidnum.ticket.repo.TicketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final FlightService flightService;

    public List<Ticket> getTickets() {
        List<Ticket> tickets = repository.findAllByDeletedAtIsNull();
        List<Integer> ids = tickets.stream().map(Ticket::getFlightId).distinct().toList();
        List<FlightModel> flights = flightService.getFlightsByIds(ids);

        for (Ticket t : tickets) {
            t.setFlight(flights.stream()
                    .filter(obj -> obj.getId().equals(t.getFlightId()))
                    .findFirst()
                    .orElse(null));
        }
        return tickets;
    }
}
