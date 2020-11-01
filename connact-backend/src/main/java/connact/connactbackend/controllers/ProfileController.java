package connact.connactbackend.controllers;

import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Profile;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.models.ProfileCreateModel;
import connact.connactbackend.repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/profile")

public class ProfileController {
    @Autowired
    private ProfileRepo profileRepo;

    @GetMapping(path = "/" )
    public Iterable<Profile> profiles() {
        return profileRepo.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateModel profileModel) {
        if (profileModel.getDisplayName()==null || profileModel.getEducation()==null ||
                profileModel.getSkills()==null || profileModel.getInterests()==null){
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        List<Skill> skills = profileModel.getSkills();
        List<Interest> interests = profileModel.getInterests();

        Profile profile = new Profile(profileModel.getDisplayName(), profileModel.getEducation(), skills, interests);
        profileRepo.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }
}
