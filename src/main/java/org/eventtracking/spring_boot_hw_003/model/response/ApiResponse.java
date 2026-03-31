package org.eventtracking.spring_boot_hw_003.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    private Instant timestamp ;
    private String message;
    private HttpStatus status;
    private T payload;
    public static <T> ApiResponse<T> success(String message,HttpStatus status, T payload) {
        return ApiResponse.<T>builder()
                .timestamp(Instant.now())
                .message(message)
                .status(status)
                .payload(payload)
                .build();
    }


}
