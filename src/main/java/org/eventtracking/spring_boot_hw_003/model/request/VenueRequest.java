package org.eventtracking.spring_boot_hw_003.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue Name is required")
    @Size(max = 100 , message = "Venue Name must be less than 100 characters")
    @Schema(defaultValue = "lord seth condo")
    private String venueName ="lord seth condo";
    @NotBlank(message = "Location is required")
    @Size(max = 150 , message = "location must be less than 150 characters")
    @Schema(defaultValue = "korean")
    private String location = "korean";
}
