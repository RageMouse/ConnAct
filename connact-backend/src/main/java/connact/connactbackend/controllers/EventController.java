package connact.connactbackend.controllers;

import connact.connactbackend.entities.Event;
import connact.connactbackend.models.EventCreateModel;
import connact.connactbackend.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/event")

public class EventController {
    @Autowired
    private EventRepo eventRepo;

    @PostMapping("/")
    public ResponseEntity<?> createAuction(@RequestBody EventCreateModel eventCreateModel) {
        if (eventCreateModel.getEventName() == null || eventCreateModel.getEventDescription() == null || eventCreateModel.getDateStart() == null || eventCreateModel.getDateEnd() == null) {
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        Event event = new Event(eventCreateModel.getEventName(), eventCreateModel.getEventDescription(), eventCreateModel.getDateStart(), eventCreateModel.getDateEnd());
        eventRepo.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
}
