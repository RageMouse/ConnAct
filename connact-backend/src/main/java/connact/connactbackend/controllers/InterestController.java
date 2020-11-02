package connact.connactbackend.controllers;

import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.repositories.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/interest")

public class InterestController {
    @Autowired
    private InterestRepo interestRepo;

    @GetMapping(path = "/" )
    public Iterable<Interest> interests() {
        return interestRepo.findAll();
    }
}
