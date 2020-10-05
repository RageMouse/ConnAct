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
    private String state;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;
}
