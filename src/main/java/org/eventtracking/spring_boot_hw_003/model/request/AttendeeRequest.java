package org.eventtracking.spring_boot_hw_003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "attendee name is required !")
    @Size(min = 1 , message = "attendee more than 1")
    @Size(max = 50 , message =  "attendee less than 50")
    @Schema(defaultValue = "lork seth")
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Venue name can only contain letters and spaces"
    )
    private String attendeeName = "lord seth";
    @NotBlank(message = "email is required !")
    @Size(max = 150 , message = "email must be between 0 to 150 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "email should valid with email format"
    )
    @Schema(defaultValue = "lordSeth@gmail.com")
    private String email = "lordSeth@gmail.com";
}
