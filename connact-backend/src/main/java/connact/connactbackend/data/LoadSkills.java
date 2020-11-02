package connact.connactbackend.data;

import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.SkillRepo;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadSkills {

    private static final Logger log = LoggerFactory.getLogger(LoadSkills.class);

    @Bean
    CommandLineRunner initSkills(SkillRepo skillRepository) {
        return args -> {
            log.info("Preloading skill..." + skillRepository.save(new Skill("Fishing")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Cooking")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Agile")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Running")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Eating")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Programming")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Racing")));
            log.info("Preloading skill..." + skillRepository.save(new Skill("Gaming")));
        };
    }
}