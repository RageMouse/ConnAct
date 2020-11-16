package connact.connactbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private Long eventId;
    private String owner;
    private String history;
    private String eventName;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private boolean active;
    @OneToOne(mappedBy = "myEvent")
    private Employee employee;

    public Event() {

    }

    public Event(String eventName, String description, Date dateStart, Date dateEnd) {
        this.eventName = eventName;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = true;
    }
}
