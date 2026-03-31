package org.eventtracking.spring_boot_hw_003.repository;

import jakarta.validation.constraints.Positive;
import jakarta.websocket.server.PathParam;
import org.apache.ibatis.annotations.*;
import org.eventtracking.spring_boot_hw_003.model.entity.Event;
import org.eventtracking.spring_boot_hw_003.model.request.EventRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface EventRepository {


    @Results(id = "eventMapper", value = {@Result(property = "eventId", column = "event_id"), @Result(property = "eventName", column = "event_name"), @Result(property = "eventDate", column = "event_date"), @Result(property = "venueId", column = "venue_id", one = @One(select = "org.eventtracking.spring_boot_hw_003.repository.VenueRepository.getVenueById")), @Result(property = "attendee", column = "event_id", many = @Many(select = "org.eventtracking.spring_boot_hw_003.repository.AttendeeRepository.getAttendeesByEventId"))})
    @Select("""
            select * from events offset #{offset} limit #{size};
            """)
    List<Event> getAllEvents(int offset, @Positive int size);

    @ResultMap("eventMapper")
    @Select("""
                select * from events where event_id = #{id};
            """)
    Optional<Event> getEventById(int id);

    @Delete("""
            delete from events where event_id = #{id};
            """)
    void deleteEventById(int id);


    @ResultMap("eventMapper")
    @Select("""
                insert into events values (default,#{req.eventName},#{req.eventDate},#{req.venueId}) returning *;
            """)
    Event createEvent(@Param("req") EventRequest req);


    @ResultMap("eventMapper")


    @Select("""
                SELECT EXISTS (
                   select 1 from events where event_name = #{eventName} and event_date = #{eventDate}
                )
            """)
    Optional<Boolean> verifyEvent(String eventName, LocalDate eventDate);


    @Insert("""
                insert into event_attendee values (#{attendeeId},#{eventId});
            """)
    void createEventAttendee(Long eventId, int attendeeId);

    @Delete("""
            delete from event_attendee where event_id = #{eventId};
            """)
    void deleteEventAttendee(int eventId);


    @ResultMap("eventMapper")
    @Update("""
                update events set event_name = #{req.eventName},event_date = #{req.eventDate},venue_id = #{req.venueId} where event_id = #{id} ;
            """)
    void updateEventById(int id, @PathParam("req") EventRequest req);


}


