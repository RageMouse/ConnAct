package connact.connactbackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue
    private Long skillId;
    private String name;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }
}
