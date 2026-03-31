package org.eventtracking.spring_boot_hw_003.service.impl;

import lombok.RequiredArgsConstructor;
import org.eventtracking.spring_boot_hw_003.exception.ApiException;
import org.eventtracking.spring_boot_hw_003.model.entity.Attendee;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeRequest;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeUpdateRequest;
import org.eventtracking.spring_boot_hw_003.repository.AttendeeRepository;
import org.eventtracking.spring_boot_hw_003.service.AttendeeService;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(int page, int size) {
        int offset = (page - 1) * size;
        return attendeeRepository.getAllAttendees(offset , size);
    }

    @Override
    public Attendee getAttendeeById(int id) {
        return attendeeRepository.getAttendeeById(id).orElseThrow(() -> ApiException.notFound("Attendee" , id));
    }

    @Override
    public void deleteAttendeeById(int id) {
        getAttendeeById(id);
        if(attendeeRepository.exitsAttendeeOnEvent(id)){
            throw ApiException.conflict("This attendee is still on some events. Remove from those events first.");
        }
        attendeeRepository.deleteAttendeeById(id);

    }

    @Override
    public Attendee createAttendee(AttendeeRequest req) {
        System.out.println(req.getEmail());
        if (attendeeRepository.exitsAttendeeEmail(req.getEmail())){
            throw ApiException.conflict("This attendee is already in an email address");
        }

        return attendeeRepository.createAttendee(req);
    }

    @Override
    public Attendee updateAttendeeById(int id, AttendeeUpdateRequest req) {
        getAttendeeById(id);
        return attendeeRepository.updateAttendeeById(id,req);
    }
}
