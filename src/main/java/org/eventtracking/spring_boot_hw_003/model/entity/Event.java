package org.eventtracking.spring_boot_hw_003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String eventName;
    private Instant eventDate;
    private Venue venueId;
    private List<Attendee> attendee;

}
