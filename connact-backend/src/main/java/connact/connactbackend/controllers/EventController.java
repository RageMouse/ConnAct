package connact.connactbackend.controllers;

import connact.connactbackend.repositories.EventRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/event")

public class EventController {
    private EventRepo eventRepo;
}
