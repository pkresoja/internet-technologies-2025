package rs.ac.singidnum.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidnum.ticket.entity.Airline;
import rs.ac.singidnum.ticket.entity.Ticket;
import rs.ac.singidnum.ticket.entity.Type;
import rs.ac.singidnum.ticket.model.FlightModel;
import rs.ac.singidnum.ticket.repo.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final FlightService flightService;
    private final AirlineService airlineService;
    private final TypeService typeService;

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

    public Optional<Ticket> getTicketById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Optional<Ticket> getTicketByIdWithFlight(Integer id) {
        Optional<Ticket> optional = getTicketById(id);
        if (optional.isEmpty())
            return Optional.empty();

        Ticket ticket = optional.get();
        FlightModel flight = flightService.getFlightById(ticket.getFlightId()).orElseThrow();
        ticket.setFlight(flight);

        return Optional.of(ticket);
    }

    public void createTicket(Ticket model) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(model.getFlightId());

        checkIfAirlineAndTypeExist(model);

        Airline airline = new Airline();
        airline.setId(model.getAirline().getId());
        ticket.setAirline(airline);

        Type type = new Type();
        type.setId(model.getType().getId());
        ticket.setType(type);

        ticket.setPrice(model.getPrice());
        ticket.setCount(model.getCount());
        ticket.setCreatedAt(LocalDateTime.now());

        repository.save(ticket);
    }

    public void updateTicket(Integer id, Ticket model) {
        Ticket ticket = getTicketById(id).orElseThrow();
        ticket.setFlightId(model.getFlightId());

        checkIfAirlineAndTypeExist(model);

        Airline airline = new Airline();
        airline.setId(model.getAirline().getId());
        ticket.setAirline(airline);

        Type type = new Type();
        type.setId(model.getType().getId());
        ticket.setType(type);

        ticket.setPrice(model.getPrice());
        ticket.setCount(model.getCount());
        ticket.setUpdatedAt(LocalDateTime.now());

        repository.save(ticket);
    }

    public void deleteTicket(Integer id) {
        Ticket ticket = getTicketById(id).orElseThrow();
        ticket.setDeletedAt(LocalDateTime.now());
        repository.save(ticket);
    }

    private void checkIfAirlineAndTypeExist(Ticket model) {
        if (!airlineService.existsById(model.getAirline().getId()))
            throw new RuntimeException("AIRLINE_NOT_FOUND");

        if (!typeService.existsById(model.getType().getId()))
            throw new RuntimeException("TYPE_NOT_FOUND");
    }

}
