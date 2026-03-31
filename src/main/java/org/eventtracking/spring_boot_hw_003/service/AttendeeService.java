package org.eventtracking.spring_boot_hw_003.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.eventtracking.spring_boot_hw_003.model.entity.Attendee;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeRequest;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeUpdateRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(@Positive int page, @Positive int size);

    Attendee getAttendeeById(@Positive(message = "ID must be greater than 0") int id);

    void deleteAttendeeById(@Positive(message = "ID must be greater than 0") int id);

    Attendee createAttendee(@Valid AttendeeRequest req);

    Attendee updateAttendeeById(@Positive(message = "ID must be greater than 0") int id, @Valid AttendeeUpdateRequest req);
}
