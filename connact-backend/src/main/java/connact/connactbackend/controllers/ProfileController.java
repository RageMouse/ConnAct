package connact.connactbackend.controllers;

import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Profile;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.models.ProfileCreateModel;
import connact.connactbackend.models.ProfileEditModel;
import connact.connactbackend.repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/profile")

public class ProfileController {
    @Autowired
    private ProfileRepo profileRepo;

    @GetMapping(path = "/" )
    public Profile profiles() {
        return profileRepo.findAll().get(0);
    }

    @GetMapping(path = "/{id}" )
    public Optional<Profile> findById(@PathVariable("id") Long id) {
        return profileRepo.findById(id);
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

    @PutMapping(path = "/")
    public ResponseEntity<?> editProfile(@RequestBody ProfileEditModel profileModel){
        List<Skill> skills = profileModel.getSkills();
        List<Interest> interests = profileModel.getInterests();

        Profile p = profileRepo.getOne(profileModel.getProfileId());
        p.setDisplayName(profileModel.getDisplayName());
        p.setEducation(profileModel.getEducation());
        p.setSkills(skills);
        p.setInterests(interests);

        profileRepo.save(p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
