package rs.ac.singidnum.ticket.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rs.ac.singidnum.ticket.model.ErrorModel;

import java.time.LocalDateTime;

@RestControllerAdvice
@CrossOrigin
public class ErrorController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModel handleException(Exception e, HttpServletRequest request) {
        ErrorModel model = new ErrorModel();
        model.setName(e.getClass().getSimpleName());
        model.setMessage(e.getMessage());
        model.setPath(request.getServletPath());
        model.setTimestamp(LocalDateTime.now());
        return model;
    }
}
