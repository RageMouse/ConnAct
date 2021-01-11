package connact.connactbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private Long eventId;
    private Long ownerId;
    private String history;
    private String eventName;
    private String description;
    private String dateStart;
    private String dateEnd;
    private boolean active;
    @OneToOne(mappedBy = "myEvent")
    private Employee employee;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "event")
    @JsonBackReference
    private List<Request> request;

    public Event() {

    }

    public Event(Long ownerId ,String eventName, String description, String dateStart, String dateEnd) {
        this.ownerId = ownerId;
        this.eventName = eventName;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = true;
    }
}
