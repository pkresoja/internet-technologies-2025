package rs.ac.singidnum.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import rs.ac.singidnum.ticket.model.FlightModel;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final RestClient client = RestClient.builder()
            .baseUrl("https://flight.pequla.com/api/flight")
            .defaultHeader("Accept", "application/json")
            .defaultHeader("X-Name", "ITWS2025")
            .build();

    public List<FlightModel> getFlights() {
        return client.get()
                .uri("/list?type=departure")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public Optional<FlightModel> getFlightById(Integer id) {
        try {
            return Optional.ofNullable(client.get()
                    .uri("/" + id)
                    .retrieve()
                    .body(FlightModel.class)
            );
        } catch (HttpClientErrorException.NotFound ex) {
            return Optional.empty();
        }
    }
}
