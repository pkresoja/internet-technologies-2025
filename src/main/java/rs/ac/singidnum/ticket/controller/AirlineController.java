package rs.ac.singidnum.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidnum.ticket.entity.Airline;
import rs.ac.singidnum.ticket.service.AirlineService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService service;

    @GetMapping
    public List<Airline> getAirlines() {
        return service.getAirlines();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getAirlineById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAirline(@RequestBody Airline airline) {
        service.createAirline(airline);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAirline(@PathVariable Integer id, @RequestBody Airline airline) {
        service.updateAirline(id, airline);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAirline(@PathVariable Integer id) {
        service.deleteAirlineById(id);
    }
}
