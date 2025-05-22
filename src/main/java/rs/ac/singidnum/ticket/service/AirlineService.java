package rs.ac.singidnum.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidnum.ticket.entity.Airline;
import rs.ac.singidnum.ticket.repo.AirlineRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirlineService {

    private final AirlineRepository repository;

    public List<Airline> getAirlines() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Airline> getAirlineById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public void createAirline(Airline model) {
        existsByNameOrWebsite(model);
        Airline airline = new Airline();
        airline.setName(model.getName());
        airline.setCountry(model.getCountry());
        airline.setWebsite(model.getWebsite());
        airline.setCreatedAt(LocalDateTime.now());
        repository.save(airline);
    }

    public void updateAirline(Integer id, Airline model) {
        Airline airline = getAirlineById(id).orElseThrow();
        if (!airline.getName().equals(model.getName()) && !airline.getWebsite().equals(model.getWebsite())) {
            existsByNameOrWebsite(model);
        }
        airline.setName(model.getName());
        airline.setCountry(model.getCountry());
        airline.setWebsite(model.getWebsite());
        airline.setUpdatedAt(LocalDateTime.now());
        repository.save(airline);
    }

    public void deleteAirlineById(Integer id) {
        Airline airline = getAirlineById(id).orElseThrow();
        airline.setDeletedAt(LocalDateTime.now());
        repository.save(airline);
    }

    public Boolean existsById(Integer id) {
        return repository.existsByIdAndDeletedAtIsNull(id);
    }

    private void existsByNameOrWebsite(Airline model) {
        if (repository.existsByWebsiteAndDeletedAtIsNull(model.getWebsite()))
            throw new RuntimeException("EXISTS_BY_WEBSITE");

        if (repository.existsByNameAndDeletedAtIsNull(model.getName()))
            throw new RuntimeException("EXISTS_BY_NAME");
    }
}
