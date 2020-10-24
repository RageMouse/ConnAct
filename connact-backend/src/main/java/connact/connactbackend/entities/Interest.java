package connact.connactbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Interest {
    @Id
    @GeneratedValue
    private Long interestId;
    private String name;

    public Interest() {
    }

    public Interest(String name) {
        this.name = name;
    }
}
