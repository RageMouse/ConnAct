package connact.connactbackend.models;

import lombok.Data;

import java.util.Date;

@Data
public class EventEditModel {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String dateStart;
    private String dateEnd;
}
