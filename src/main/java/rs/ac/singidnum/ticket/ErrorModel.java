package rs.ac.singidnum.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ErrorModel {
    private String message;
    private LocalDateTime timestamp;
}
