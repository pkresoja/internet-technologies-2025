package rs.ac.singidnum.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidnum.ticket.entity.Type;
import rs.ac.singidnum.ticket.repo.TypeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository repository;

    public List<Type> getTypes() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Type> getTypeById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public void createType(Type model) {
        if (repository.existsByNameAndDeletedAtIsNull(model.getName()))
            throw new RuntimeException("TYPE_EXISTS_BY_NAME");

        Type type = new Type();
        type.setName(model.getName());
        type.setPrice(model.getPrice());
        type.setCreatedAt(LocalDateTime.now());
        repository.save(type);
    }

    public void updateType(Integer id, Type model) {
        Type type = getTypeById(id).orElseThrow();
        type.setName(model.getName());
        type.setPrice(model.getPrice());
        type.setUpdatedAt(LocalDateTime.now());
        repository.save(type);
    }

    public void deleteType(Integer id) {
        Type type = getTypeById(id).orElseThrow();
        type.setDeletedAt(LocalDateTime.now());
        repository.save(type);
    }
}
