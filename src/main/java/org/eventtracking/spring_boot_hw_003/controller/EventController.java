package org.eventtracking.spring_boot_hw_003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.model.entity.Event;
import org.eventtracking.spring_boot_hw_003.model.request.EventRequest;
import org.eventtracking.spring_boot_hw_003.model.response.ApiResponse;
import org.eventtracking.spring_boot_hw_003.repository.EventRepository;
import org.eventtracking.spring_boot_hw_003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
@Validated
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") int page, @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") int size) {
        List<Event> events = eventService.getAllEvents(page, size);
        return ResponseEntity.ok(ApiResponse.success("Retrieved All events successfully", HttpStatus.OK, events));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable(name = "eventId") @Positive(message = "id must be greater than 0") int id) {

        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved event successfully", HttpStatus.OK, event));

    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable(name = "eventId") @Positive(message = "id must be greater than 0") int id) {

        eventService.deleteEventById(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted event with id " + id + " successfully", HttpStatus.OK, null));

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody EventRequest req) {
        Event event = eventService.createEven(req);


        return ResponseEntity.ok(ApiResponse.success("Created event successfully ", HttpStatus.CREATED, event));
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable(name = "eventId") @Positive(message = "id must be greater than 0") int id, @Valid @RequestBody EventRequest req) {
        Event event = eventService.updateEventById(id,req);


        return ResponseEntity.ok(ApiResponse.success("update event successfully ", HttpStatus.CREATED, event));
    }


}
