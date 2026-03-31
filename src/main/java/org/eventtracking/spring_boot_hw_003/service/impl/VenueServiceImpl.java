package org.eventtracking.spring_boot_hw_003.service.impl;

import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.exception.ApiException;
import org.eventtracking.spring_boot_hw_003.model.entity.Venue;
import org.eventtracking.spring_boot_hw_003.model.request.VenueRequest;
import org.eventtracking.spring_boot_hw_003.repository.VenueRepository;
import org.eventtracking.spring_boot_hw_003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(int page, int size) {
        int offset = (page - 1) * size;
        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue getVenueById(int id) {
        return venueRepository.getVenueById(id).orElseThrow(() -> ApiException.notFound("Venue", id));

    }

    @Override
    public void deleteByVenueId(int id) {
        getVenueById(id);
        if (venueRepository.exitsVenueOnEvent(id)) {
            throw ApiException.conflict("Some events still use this venue. Update or delete those events first.");
        }
        venueRepository.deleteByVenueId(id);


    }

    @Override
    public Venue createVenue(VenueRequest req) {
        if (venueRepository.exitsVenueName(req.getVenueName())) {
            throw ApiException.conflict("Venue with name " + req.getVenueName() + " already exists.");
        }
        return venueRepository.createVenue(req);
    }

    @Override
    public Venue updateVenueById(int id, VenueRequest req) {
        getVenueById(id);
        if (venueRepository.exitsVenueName(req.getVenueName())) {
            throw ApiException.conflict("Venue with name " + req.getVenueName() + " already exists.");
        }
        return venueRepository.updateVenueById(id, req);
    }
}
