package rs.ac.singidnum.ticket.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidnum.ticket.model.ErrorModel;

import java.time.LocalDateTime;

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
}
