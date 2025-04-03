package rs.ac.singidnum.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidnum.ticket.entity.Airline;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    List<Airline> findAllByDeletedAtIsNull();

    Optional<Airline> findByIdAndDeletedAtIsNull(Integer id);

    Optional<Airline> findByWebsiteAndDeletedAtIsNull(String website);

    Optional<Airline> findByNameAndDeletedAtIsNull(String name);
}
