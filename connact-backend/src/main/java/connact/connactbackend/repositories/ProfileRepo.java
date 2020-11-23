package connact.connactbackend.repositories;

import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Profile getByProfileId(Long eventId);
}
