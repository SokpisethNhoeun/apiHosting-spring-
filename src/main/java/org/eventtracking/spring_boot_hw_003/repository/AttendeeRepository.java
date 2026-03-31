package org.eventtracking.spring_boot_hw_003.repository;

import org.apache.ibatis.annotations.*;
import org.eventtracking.spring_boot_hw_003.model.entity.Attendee;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeRequest;
import org.eventtracking.spring_boot_hw_003.model.request.AttendeeUpdateRequest;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper" , value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName" , column = "attendee_name")
    })
    @Select("""
    select * from attendees offset #{offset} limit #{size};
""")
    List<Attendee> getAllAttendees(int offset, int size);

    @ResultMap("attendeeMapper")
    @Select("""
    select * from attendees where attendee_id = #{id}
""")
    Optional<Attendee> getAttendeeById(int id);

    @Delete("""
delete from attendees where attendee_id = #{id}
""")
    void deleteAttendeeById(int id);



    @Select("""
                SELECT EXISTS (
                    SELECT 1 FROM event_attendee WHERE attendee_id = #{id}
                )
            """)
    Boolean exitsAttendeeOnEvent(int id);
    @Select("""
                SELECT EXISTS (
                    SELECT * FROM attendees WHERE email = #{attendeeEmail}
                )
            """)
    Boolean exitsAttendeeEmail(String attendeeEmail);


    @ResultMap("attendeeMapper")
    @Select("""
insert into attendees values (default,#{req.attendeeName},#{req.email}) returning *;
""")
    Attendee createAttendee(@Param("req") AttendeeRequest req);

    @ResultMap("attendeeMapper")
    @Select("""
update attendees set attendee_name = #{req.attendeeName} where attendee_id = #{id} returning *;
    
""")
    Attendee updateAttendeeById(int id, @Param("req") AttendeeUpdateRequest req);




    @ResultMap("attendeeMapper")
    @Select("""
            select
                   at.attendee_id,
                   at.attendee_name,
                   at.email
            from events e
                     inner join event_attendee ea
                                on e.event_id = ea.event_id
                     inner join attendees at
                                on at.attendee_id = ea.attendee_id
            where e.event_id = #{eventId};
            """)
    List<Attendee> getAttendeesByEventId(int eventId);
}
