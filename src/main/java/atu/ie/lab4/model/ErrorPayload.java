package atu.ie.lab4.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorPayload {

    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private Map<String, String> details;
}

