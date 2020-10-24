package connact.connactbackend.repositories;

import connact.connactbackend.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepo extends JpaRepository<Interest, Long> {
}
