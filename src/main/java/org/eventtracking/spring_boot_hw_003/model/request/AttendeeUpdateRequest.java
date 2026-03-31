package org.eventtracking.spring_boot_hw_003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeUpdateRequest {
    @NotBlank(message = "attendee name is required !")
    @Size(min = 1 , message = "attendee more than 1")
    @Size(max = 50 , message =  "attendee less than 50")
    @Schema(defaultValue = "lork seth")
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Venue name can only contain letters and spaces"
    )
    private String attendeeName = "lord seth";
}
