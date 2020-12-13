package connact.connactbackend.controllers;

import connact.connactbackend.entities.*;
import connact.connactbackend.models.ProfileCreateModel;
import connact.connactbackend.models.ProfileEditModel;
import connact.connactbackend.repositories.EmployeeRepo;
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

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping(path = "/" )
    public Iterable<Profile> profiles() {
        return profileRepo.findAll();
    }

    @GetMapping(path = "/{employeeId}" )
    public Profile getMyProfile(@PathVariable("employeeId") Long employeeId) {
        return profileRepo.findByEmployee_employeeId(employeeId);
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody ProfileCreateModel profileModel) {
        if (profileModel.getDisplayName()==null || profileModel.getEducation()==null ||
                profileModel.getSkills()==null || profileModel.getInterests()==null){
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        List<Skill> skills = profileModel.getSkills();
        List<Interest> interests = profileModel.getInterests();
        Employee employee = employeeRepo.findByEmployeeId(profileModel.getEmployeeId());
        Profile profile = new Profile(profileModel.getDisplayName(), profileModel.getEducation(), employee, skills, interests);

        profileRepo.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @PutMapping(path = "/")
    public ResponseEntity<?> editProfile(@RequestBody ProfileEditModel profileModel){
        List<Skill> skills = profileModel.getSkills();
        List<Interest> interests = profileModel.getInterests();

        Profile p = profileRepo.getByProfileId(profileModel.getProfileId());
        p.setDisplayName(profileModel.getDisplayName());
        p.setEducation(profileModel.getEducation());
        p.setSkills(skills);
        p.setInterests(interests);

        profileRepo.save(p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
