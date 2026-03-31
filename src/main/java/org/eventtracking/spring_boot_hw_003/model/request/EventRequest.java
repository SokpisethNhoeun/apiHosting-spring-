package org.eventtracking.spring_boot_hw_003.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "Even name is required")
    @Size(max = 100, message = "Even name must be less than 100 characters")
    @Schema(defaultValue = "HRD monthly party")
    private String eventName = "HRD monthly party";

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(defaultValue = "2026-03-30")
    @Future(message = "Event date must be in the future")
    private LocalDate eventDate = LocalDate.of(2026, 3, 30);
    @Positive(message = "Must be a positive number")
    @Schema(defaultValue = "12345")
    private int venueId = 12345;
    @NotEmpty(message = "Attendees id is required!")
    @Schema(defaultValue = "[\n 12345 \n]")
    private List<@Positive(message = "Must be a positive number") Integer> attendeesId = List.of(12345);
}
