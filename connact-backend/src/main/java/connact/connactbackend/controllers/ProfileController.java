package connact.connactbackend.controllers;

import connact.connactbackend.repositories.ProfileRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/profile")

public class ProfileController {
    private ProfileRepo profileRepo;
}
