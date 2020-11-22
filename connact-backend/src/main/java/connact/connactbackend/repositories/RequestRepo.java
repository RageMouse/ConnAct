package connact.connactbackend.repositories;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
    Iterable<Request> findAllByEventAndAcceptedAndRequesttype(Event event, Boolean accepted, String requesttype);
    Iterable<Request> findAllByEventAndAccepted(Event event, Boolean accepted);
}
