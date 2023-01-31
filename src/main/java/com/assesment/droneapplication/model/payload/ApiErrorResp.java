package com.assesment.droneapplication.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResp {

    private LocalDateTime timestamp;
    private String source;
    private int status;
    private String message;
    private String path;
    private Map<String, String> errors = new HashMap<>();

    public ApiErrorResp(int status, String message, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.source = "drone-service";
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors.put("error", error);
    }

    public ApiErrorResp(int status, String message, Map<String, String> errors, String path) {
        this.timestamp = LocalDateTime.now();
        this.source = "drone-service";
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

}
