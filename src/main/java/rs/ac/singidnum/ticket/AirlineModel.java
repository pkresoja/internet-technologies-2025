package rs.ac.singidnum.ticket;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AirlineModel {
    private Integer id;
    private String name;
    private String countryOfOrigin;
}
