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

    private static final String API_BASE = "https://flight.pequla.com/api/flight";
    private final RestClient client = RestClient.create();

    public List<FlightModel> getFlights() {
        return client.get()
                .uri(API_BASE + "/list?type=departure")
                .header("Accept", "application/json")
                .header("X-Name", "ITWS2025")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public Optional<FlightModel> getFlightById(Integer id) {
        try {
            return Optional.ofNullable(client.get()
                    .uri(API_BASE + "/" + id)
                    .header("Accept", "application/json")
                    .header("X-Name", "ITWS2025")
                    .retrieve()
                    .body(FlightModel.class)
            );
        } catch (HttpClientErrorException.NotFound ex) {
            return Optional.empty();
        }
    }
}
