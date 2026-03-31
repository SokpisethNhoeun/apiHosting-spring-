package org.eventtracking.spring_boot_hw_003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private Long attendeeId;
    private String attendeeName;
    private String email;
}
