package rs.ac.singidnum.ticket;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class BaseController {

    @GetMapping(path = "/")
    public ErrorModel home() {
        ErrorModel model = new ErrorModel();
        model.setMessage("Not Found");
        model.setTimestamp(LocalDateTime.now());
        return model;
    }

    @GetMapping(path = "/api/airline")
    public List<AirlineModel> getAirlines() {
        return List.of(
                new AirlineModel(1,"AirSerbia","Serbia"),
                new AirlineModel(2,"Fly Emirates","UAE"),
                new AirlineModel(3,"Luft Hansa","Germany"),
                new AirlineModel(4,"JAT Airlines","Yugoslavia"),
                new AirlineModel(1,"Qatar Airlines","Qatar"),
                new AirlineModel(1,"Singapore Airlines","Singapore")
        );
    }
}
