package org.eventtracking.spring_boot_hw_003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.model.entity.Attendee;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeRequest;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeUpdateRequest;
import org.eventtracking.spring_boot_hw_003.model.response.ApiResponse;
import org.eventtracking.spring_boot_hw_003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendee")
@Validated
public class AttendeeController {
    private final AttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") int page , @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") int size) {
        List<Attendee> attendees = attendeeService.getAllAttendees(page,size);
        return ResponseEntity.ok(ApiResponse.success("Retrieved attendees successfully", HttpStatus.OK ,attendees));
    }
    @GetMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable(name = "attendeeId") @Positive(message = "ID must be greater than 0") int id) {
        Attendee attendee = attendeeService.getAttendeeById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved attendees successfully", HttpStatus.OK ,attendee));
    }
    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@PathVariable(name = "attendeeId") @Positive(message = "ID must be greater than 0") int id) {

        attendeeService.deleteAttendeeById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved attendees successfully",HttpStatus.OK , null));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody @Valid AttendeeRequest req) {
        Attendee attendee = attendeeService.createAttendee(req);
        return ResponseEntity.ok(ApiResponse.success("Created attendee successfully" ,HttpStatus.CREATED ,attendee));
    }
    @PutMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById (@PathVariable(name = "attendeeId") @Positive(message = "ID must be greater than 0") int id, @RequestBody @Valid AttendeeUpdateRequest req) {
        Attendee attendee = attendeeService.updateAttendeeById(id,req);
        return ResponseEntity.ok(ApiResponse.success("Updated attendee successfully",HttpStatus.OK ,attendee));
    }





}
