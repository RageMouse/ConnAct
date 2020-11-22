package connact.connactbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name ="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name ="event_id")
    private Event event;

    private String requesttype;
    private Boolean accepted;


    public Request() {

    }
    public Request(Employee employee, Event event, String requesttype, Boolean accepted) {
        this.employee = employee;
        this.event = event;
        this.requesttype = requesttype;
        this.accepted = accepted;
    }


}
