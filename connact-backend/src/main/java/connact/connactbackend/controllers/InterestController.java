package connact.connactbackend.controllers;

import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.InterestRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/interest")

public class InterestController {
    private InterestRepo interestRepo;

    @GetMapping(path = "/" )
    public Iterable<Interest> interests() {
        return interestRepo.findAll();
    }
}
