package rs.ac.singidnum.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidnum.ticket.model.FlightModel;
import rs.ac.singidnum.ticket.service.FlightService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/flight")
@CrossOrigin
@RequiredArgsConstructor
public class FlightController {

    private final FlightService service;

    @GetMapping
    public List<FlightModel> getFlights() {
        return service.getFlights();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FlightModel> getFlightById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getFlightById(id));
    }
}
