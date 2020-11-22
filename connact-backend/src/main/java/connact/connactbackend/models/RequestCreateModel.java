package connact.connactbackend.models;

import lombok.Data;

@Data
public class RequestCreateModel {
    private Long employeeId;
    private Long eventId;
    private String requesttype;
    private Boolean accepted;
}
