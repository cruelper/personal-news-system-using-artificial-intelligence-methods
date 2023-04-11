package ru.nuykin.diplom.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResponseError {
    private final HttpStatus status;

    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    public ResponseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}