package org.eventtracking.spring_boot_hw_003.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.eventtracking.spring_boot_hw_003.model.entity.Event;
import org.eventtracking.spring_boot_hw_003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(@Positive int page, @Positive int size);

    Event getEventById(@Positive(message = "page must be greater than 0") int id);

    void deleteEventById(@Positive(message = "id must be greater than 0") int id);

    Event createEven(@Valid EventRequest req);

    Event updateEventById(@Positive(message = "id must be greater than 0") int id, @Valid EventRequest req);
}
