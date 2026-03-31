package org.eventtracking.spring_boot_hw_003.repository;

import org.apache.ibatis.annotations.*;
import org.eventtracking.spring_boot_hw_003.model.entity.Venue;
import org.eventtracking.spring_boot_hw_003.model.request.VenueRequest;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    @Select("""
                select * from venues  offset #{offset} limit #{size}
            """)
    List<Venue> getAllVenues(int offset, int size);


    @ResultMap(value = "venueMapper")
    @Select("""
                select * from venues where venue_id = #{venueId};
            """)
    Optional<Venue> getVenueById(int id);


    @ResultMap(value = "venueMapper")
    @Select("""
                delete from venues where venue_id=#{id} returning *;
            """)
    Optional<Venue> deleteByVenueId(int id);

    @Select("""
                SELECT EXISTS (
                    SELECT 1 FROM events WHERE venue_id = #{id}
                )
            """)
    Boolean exitsVenueOnEvent(int id);

    @ResultMap(value = "venueMapper")
    @Select("""
                insert into venues values (default,#{req.venueName},#{req.location}) returning *;
            """)
    Venue createVenue(@Param("req") VenueRequest req);

    @Select("""
                SELECT EXISTS (
                    SELECT * FROM venues WHERE venue_name = #{venueName}
                )
            """)
    Boolean exitsVenueName(String venueName);

    @ResultMap(value = "venueMapper")
    @Select("""
                update venues set venue_name = #{req.venueName} , location = #{req.location} where venue_id = #{id} returning *;
            """)
    Venue updateVenueById(int id, VenueRequest req);
}
