package connact.connactbackend.data;

import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.InterestRepo;
import connact.connactbackend.repositories.SkillRepo;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadInterests {

    private static final Logger log = LoggerFactory.getLogger(LoadInterests.class);

    @Bean
    CommandLineRunner initInterests(InterestRepo interestRepo) {
        return args -> {
            log.info("Preloading skill..." + interestRepo.save(new Interest("Food")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Health")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Gaming")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Cars")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Sports")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Technology")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Economics")));
            log.info("Preloading skill..." + interestRepo.save(new Interest("Politics")));
        };
    }
}