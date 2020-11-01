package connact.connactbackend.controllers;

import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.SkillRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/skill")

public class SkillController {
    private SkillRepo skillRepo;

    @GetMapping(path = "/" )
    public Iterable<Skill> skills() {
        return skillRepo.findAll();
    }
}
