package connact.connactbackend.models;

import lombok.Data;

import java.util.Date;

@Data
public class EventCreateModel {
    private Long ownerId;
    private String eventName;
    private String eventDescription;
    private Date dateStart;
    private Date dateEnd;
}
