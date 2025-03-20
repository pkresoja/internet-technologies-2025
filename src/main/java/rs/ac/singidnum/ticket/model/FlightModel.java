package rs.ac.singidnum.ticket.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlightModel {
    private Integer id;
    private TypeModel type;
    private String flightKey;
    private String flightNumber;
    private String destination;
    private String scheduledAt;
    private String estimatedAt;
    private String connectedType;
    private String connectedFlight;
    private String plane;
    private String gate;
    private String terminal;
}
