package connact.connactbackend.controllers;

import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Profile;
import connact.connactbackend.entities.Skill;
import connact.connactbackend.models.EventCreateModel;
import connact.connactbackend.models.EventEditModel;
import connact.connactbackend.models.ProfileEditModel;
import connact.connactbackend.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/event")

public class EventController {
    @Autowired
    private EventRepo eventRepo;

    @GetMapping(path = "/" )
    public Iterable<Event> events() {
        return eventRepo.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> createEvent(@RequestBody EventCreateModel eventCreateModel) {
        if (eventCreateModel.getEventName() == null || eventCreateModel.getEventDescription() == null || eventCreateModel.getDateStart() == null || eventCreateModel.getDateEnd() == null) {
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }
        Event event = new Event(eventCreateModel.getOwnerId(), eventCreateModel.getEventName(), eventCreateModel.getEventDescription(), eventCreateModel.getDateStart(), eventCreateModel.getDateEnd());
        eventRepo.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public Iterable<Event> events(@PathVariable("userId") Long id) {
        return eventRepo.findEventsByOwnerId(id);
    }

    @PutMapping(path = "/")
    public ResponseEntity<?> editEvent(@RequestBody EventEditModel eventEditModel){
        Event event = eventRepo.getOne(eventEditModel.getEventId());

        event.setEventName(eventEditModel.getEventName());
        event.setDescription(eventEditModel.getEventDescription());
        event.setDateStart(eventEditModel.getDateStart());
        event.setDateEnd(eventEditModel.getDateEnd());

        eventRepo.save(event);
        return new ResponseEntity<>(event, HttpStatus.ACCEPTED);
    }
}
