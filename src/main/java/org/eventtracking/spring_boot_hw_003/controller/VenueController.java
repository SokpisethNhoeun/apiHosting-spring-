package org.eventtracking.spring_boot_hw_003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.model.entity.Venue;
import org.eventtracking.spring_boot_hw_003.model.request.VenueRequest;
import org.eventtracking.spring_boot_hw_003.model.response.ApiResponse;
import org.eventtracking.spring_boot_hw_003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venue")
@Validated
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive( message = "must be greater than 0 ") int page, @RequestParam(defaultValue = "10") @Positive(message = "must be greater than 0") int size) {
        List<Venue> venues = venueService.getAllVenues(page,size);
        return ResponseEntity.ok(ApiResponse.success("Retrieved venues successfully", HttpStatus.OK , venues));
    }
    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> getVenuesById(@PathVariable(name = "venueId") @Positive(message = "ID must be greater than 0") int id ) {
        Venue venues = venueService.getVenueById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved venues successfully",HttpStatus.OK , venues));
    }
    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Void>> deleteByVenueId (@PathVariable(name = "venueId") @Positive int id) {
        venueService.deleteByVenueId(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted venue with id "+ id + " successfully",HttpStatus.OK ,null));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(@RequestBody @Valid VenueRequest req) {
        Venue venue = venueService.createVenue(req);
        return ResponseEntity.ok(ApiResponse.success("Created venue successfully",HttpStatus.CREATED , venue));
    }
    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable(name = "venueId") @Positive int id,@RequestBody @Valid VenueRequest req) {
        Venue venue = venueService.updateVenueById(id,req);
        return ResponseEntity.ok(ApiResponse.success("Update Venue successfully",HttpStatus.OK ,venue));
    }

}


