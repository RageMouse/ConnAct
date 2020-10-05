package connact.connactbackend.controllers;

import connact.connactbackend.repositories.SkillRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/skill")

public class SkillController {
    private SkillRepo skillRepo;
}
