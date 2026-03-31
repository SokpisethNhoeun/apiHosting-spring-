package org.eventtracking.spring_boot_hw_003.service.impl;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.exception.ApiException;
import org.eventtracking.spring_boot_hw_003.model.entity.Event;
import org.eventtracking.spring_boot_hw_003.model.request.EventRequest;
import org.eventtracking.spring_boot_hw_003.repository.EventRepository;
import org.eventtracking.spring_boot_hw_003.service.AttendeeService;
import org.eventtracking.spring_boot_hw_003.service.EventService;
import org.eventtracking.spring_boot_hw_003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final VenueService venueService;
    private final AttendeeService attendeeService;
    @Override
    public List<Event> getAllEvents(@Positive int page, @Positive int size) {
        int offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset,size);
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.getEventById(id).orElseThrow(()-> ApiException.notFound("Event",id));
    }

    @Override
    public void deleteEventById(int id) {
        getEventById(id);
        eventRepository.deleteEventById(id);
    }

    @Override
    public Event createEven(EventRequest req) {
        System.out.println(req.getEventName() + " " + req.getEventDate());

        Optional<Boolean> verifyEvent =eventRepository.verifyEvent(req.getEventName(),req.getEventDate());

        if(verifyEvent.isPresent()){
            throw ApiException.conflict("Event name with this data already exists");
        }
        venueService.getVenueById(req.getVenueId());
        for (int id:req.getAttendeesId()
             ) {
            attendeeService.getAttendeeById(id);
        }

        Event event = eventRepository.createEvent(req);

        for (int attendeeId:req.getAttendeesId()
             ) {
        eventRepository.createEventAttendee(event.getEventId(),attendeeId);
        }
        return eventRepository.getEventById(Math.toIntExact(event.getEventId())).orElseThrow(()-> ApiException.notFound("Event", Math.toIntExact(event.getEventId())));
    }

    @Override
    public Event updateEventById(int id, EventRequest req) {
        getEventById(id);
        Optional<Boolean> verifyEvent =eventRepository.verifyEvent(req.getEventName(),req.getEventDate());
        if(verifyEvent.isPresent()){
            throw ApiException.conflict("Event name with this data already exists");
        }
        venueService.getVenueById(req.getVenueId());
        for (int attendeeId : req.getAttendeesId()
        ) {
            attendeeService.getAttendeeById(attendeeId);
        }

         eventRepository.updateEventById(id,req);
        eventRepository.deleteEventAttendee(id);
        for (int attendeeId : req.getAttendeesId()
        ) {
            eventRepository.createEventAttendee((long) id,attendeeId);
        }

        return getEventById(id);
    }
}
