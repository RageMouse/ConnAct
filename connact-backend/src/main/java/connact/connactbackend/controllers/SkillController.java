package connact.connactbackend.controllers;

import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/skill")

public class SkillController {
    @Autowired
    private SkillRepo skillRepo;

    @GetMapping(path = "/" )
    public Iterable<Skill> skills() {
        return skillRepo.findAll();
    }
}
