package connact.connactbackend.repositories;

import connact.connactbackend.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Long> {
}
