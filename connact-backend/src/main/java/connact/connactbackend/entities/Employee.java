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
    private String test;
    @Lob
    private byte[] salt;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "profileId" /*, referencedColumnName = "employeeId"*/)
    private Profile profile;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_events",
            joinColumns = {@JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "eventId")})
    private List<Event> joinedEvents;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "eventId" /*, referencedColumnName = "employeeId"*/)
    private Event myEvent;

    public Employee() {
    }

    public Employee(String userName, String password,byte[] salt) {
        this.userName = userName;                            
        this.password = password;
        this.salt = salt;
    }
}
