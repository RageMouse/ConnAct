package connact.connactbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String salt;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "employee", orphanRemoval = true)
    @JoinColumn(name = "profileId")
    @JsonManagedReference
    private Profile profile;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employee_events",
            joinColumns = {@JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "eventId")})
    private List<Event> joinedEvents;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "eventId" /*, referencedColumnName = "employeeId"*/)
    private Event myEvent;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "employee")
    @JsonBackReference
    private List<Request> request;

    public Employee() {
    }

    public Employee(String userName, String password,String salt) {
        this.userName = userName;                            
        this.password = password;
        this.salt = salt;
    }

    public Employee(Long employeeId, Profile profile) {
        this.employeeId = employeeId;
        this.profile = profile;
    }
}
