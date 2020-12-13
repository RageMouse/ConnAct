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
public class Profile {
    @Id
    @GeneratedValue
    private Long profileId;
    private String displayName;
    private String education;
    @JsonBackReference
    @OneToOne
    private Employee employee;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profile_skills",
            joinColumns = {@JoinColumn(name = "profileId")},
            inverseJoinColumns = {@JoinColumn(name = "skillId")})
    private List<Skill> skills;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "profile_interests",
            joinColumns = {@JoinColumn(name = "profileId")},
            inverseJoinColumns = {@JoinColumn(name = "interestId")})
    private List<Interest> interests;

    public Profile() {
    }

    public Profile(String displayName, String education) {
        this.displayName = displayName;
        this.education = education;
    }

    public Profile(String displayName, String education, List<Skill> skills, List<Interest> interests) {
        this.displayName = displayName;
        this.education = education;
        this.skills = skills;
        this.interests = interests;
    }

    public Profile(String displayName, String education, Employee employee, List<Skill> skills, List<Interest> interests) {
        this.displayName = displayName;
        this.education = education;
        this.employee = employee;
        this.skills = skills;
        this.interests = interests;
    }
}
