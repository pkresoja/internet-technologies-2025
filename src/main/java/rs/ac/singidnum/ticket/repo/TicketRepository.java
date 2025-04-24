package rs.ac.singidnum.ticket.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidnum.ticket.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findAllByDeletedAtIsNull();

    Optional<Ticket> findByIdAndDeletedAtIsNull(Integer id);
}
