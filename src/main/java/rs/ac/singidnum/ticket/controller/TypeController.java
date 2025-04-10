package rs.ac.singidnum.ticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidnum.ticket.entity.Type;
import rs.ac.singidnum.ticket.service.TypeService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService service;

    @GetMapping
    public List<Type> getTypes() {
        return service.getTypes();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getTypeById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createType(@RequestBody Type type) {
        service.createType(type);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateType(@PathVariable Integer id, @RequestBody Type type) {
        service.updateType(id, type);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteType(@PathVariable Integer id) {
        service.deleteType(id);
    }
}
