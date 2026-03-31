package org.eventtracking.spring_boot_hw_003.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private final String title;
    private final String type;
    private final HttpStatus status;

    public ApiException(String detail, String title, String type, HttpStatus status) {
        super(detail);
        this.title = title;
        this.type = type;
        this.status = status;
    }

    public static ApiException notFound(String title, int id) {
        return new ApiException(title + " with id " + id + " not found",
                "Not Found",
                "http://localhost:8080/errors/not-found",
                HttpStatus.NOT_FOUND);
    }

    public static ApiException conflict(String title) {
        return new ApiException(title,
                "Conflict",
                "http://localhost:8080/errors/operation-not-allowed",
                HttpStatus.CONFLICT);
    }


}


