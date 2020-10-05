package connact.connactbackend.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private Long employeeId;
    private String userName;
    private String password;
    private Profile profile;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_events",
            joinColumns = {@JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "eventId")})
    private List<Event> joinedEvents;

    public Employee() {
    }

    public Employee(String userName, String password) {
        this.userName = userName;                            
        this.password = password;
    }
}
