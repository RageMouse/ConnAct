package connact.connactbackend.repositories;

import connact.connactbackend.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
//    Profile findByID(Long id);
}
