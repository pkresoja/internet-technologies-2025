package rs.ac.singidnum.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidnum.ticket.entity.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type> findAllByDeletedAtIsNull();

    Optional<Type> findByIdAndDeletedAtIsNull(Integer id);

    boolean existsByNameAndDeletedAtIsNull(String name);
}
