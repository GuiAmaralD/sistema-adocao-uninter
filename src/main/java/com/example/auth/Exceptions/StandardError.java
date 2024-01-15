package com.example.auth.Exceptions;


import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatusCode;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private HttpStatusCode status;
    private String message;
    private String path;

    public StandardError() {
    }

    public StandardError(Instant timestamp, HttpStatusCode status, String message, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public static StandardError init(HttpStatusCode status, String message, String path){
        return new StandardError(Instant.now(), status, message, path);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
