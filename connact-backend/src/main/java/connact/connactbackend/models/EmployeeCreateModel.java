package connact.connactbackend.models;

import lombok.Data;

@Data
public class EmployeeCreateModel {
    private String userName;
    private String password;
    private String salt;
}
