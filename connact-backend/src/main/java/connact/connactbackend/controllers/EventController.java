package connact.connactbackend.controllers;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.entities.Event;
import connact.connactbackend.entities.Request;
import connact.connactbackend.models.EventCreateModel;

import connact.connactbackend.models.RequestCreateModel;
import connact.connactbackend.models.RequestEditModel;
import connact.connactbackend.repositories.EmployeeRepo;

import connact.connactbackend.models.EventEditModel;

import connact.connactbackend.repositories.EventRepo;
import connact.connactbackend.repositories.RequestRepo;
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
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private RequestRepo requestRepo;

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
        event = eventRepo.save(event);
        eventRepo.flush();

        Request request = new Request(employeeRepo.findById(eventCreateModel.getOwnerId()).get(), event,"request",true);
        requestRepo.save(request);
        return new ResponseEntity<>(event, HttpStatus.CREATED);

    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> closeEvent(@PathVariable Long eventId){
        Event event = eventRepo.getByEventId(eventId);
        event.setActive(false);
        eventRepo.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<?> createRequest(@RequestBody RequestCreateModel requestCreateModel) {
        if (requestCreateModel.getEmployeeId()==null || requestCreateModel.getEventId()==null ||
                requestCreateModel.getAccepted()==null || requestCreateModel.getRequesttype()==null){
            return new ResponseEntity<Error>(HttpStatus.NO_CONTENT);
        }

        Event event = eventRepo.findById(requestCreateModel.getEventId()).get();
        Employee employee = employeeRepo.findById(requestCreateModel.getEmployeeId()).get();


        Request request = new Request(employee, event,requestCreateModel.getRequesttype(),requestCreateModel.getAccepted());
        requestRepo.save(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping(path ="/requests/{id}")
    public Iterable<Request> requestsUser(@PathVariable Long id) {

        return requestRepo.findAllByEventAndAcceptedAndRequesttype(eventRepo.findById(id).get(),false,"request");
    }
    @GetMapping(path ="/invites/{id}")
    public Iterable<Request> invitesUser(@PathVariable Long id) {

        return requestRepo.findAllByEmployeeAndAcceptedAndRequesttype(employeeRepo.findById(id).get(),false,"uitnodiging");
    }
    @GetMapping(path ="/users/{id}")
    public Iterable<Request> requestsAcceptedUsers(@PathVariable Long id) {

        return requestRepo.findAllByEventAndAccepted(eventRepo.findById(id).get(),true);
    }
    @PutMapping(path = "/accept")
    public ResponseEntity<?> acceptRequest(@RequestBody RequestEditModel requestEditModel){

        Request request = requestRepo.findById(requestEditModel.getId()).get();

        request.setAccepted(requestEditModel.getAccepted());

        requestRepo.save(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @DeleteMapping(path = "/requests/kick/{id}")
    public ResponseEntity<?> kickEmployee(@PathVariable Long id){

        Request request = requestRepo.findById(id).get();


        requestRepo.delete(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public Iterable<Event> events(@PathVariable Long userId) {
        return eventRepo.findEventsByOwnerId(userId);
    }

    @PutMapping(path = "/")
    public ResponseEntity<?> editEvent(@RequestBody EventEditModel eventEditModel){
        Event event = eventRepo.getByEventId(eventEditModel.getEventId());

        event.setEventName(eventEditModel.getEventName());
        event.setDescription(eventEditModel.getEventDescription());
        event.setDateStart(eventEditModel.getDateStart());
        event.setDateEnd(eventEditModel.getDateEnd());

        eventRepo.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{eventId}")
    public void deleteEvent(@PathVariable Long eventId){
        Event event = eventRepo.getByEventId(eventId);
        eventRepo.delete(event);
    }
}
