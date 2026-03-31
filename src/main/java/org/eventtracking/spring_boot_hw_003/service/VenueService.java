package org.eventtracking.spring_boot_hw_003.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.eventtracking.spring_boot_hw_003.model.entity.Venue;
import org.eventtracking.spring_boot_hw_003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(@Positive( message = "must be greater than 0 ") int page, @Positive( message = "must be greater than 0") int size);

    Venue getVenueById(@Positive(message = "ID must be greater than 0") int id);

    void deleteByVenueId(@Positive int id);

    Venue createVenue(VenueRequest req);

    Venue updateVenueById(@Positive int id, @Valid VenueRequest req);
}
