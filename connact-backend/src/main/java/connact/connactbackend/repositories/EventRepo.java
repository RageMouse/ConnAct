package connact.connactbackend.repositories;

import connact.connactbackend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
    Event getByEventId(Long eventId);
    List<Event> findEventsByOwnerId(@Param("employeeId")Long employeeId);
}
