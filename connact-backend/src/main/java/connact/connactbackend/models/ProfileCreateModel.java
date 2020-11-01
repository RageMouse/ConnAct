package connact.connactbackend.models;

import connact.connactbackend.entities.Employee;
import connact.connactbackend.entities.Interest;
import connact.connactbackend.entities.Skill;
import lombok.Data;

import java.util.List;

@Data
public class ProfileCreateModel {
    private String displayName;
    private String education;
    private Employee employee;
    private List<Skill> skills;
    private List<Interest> interests;
}
