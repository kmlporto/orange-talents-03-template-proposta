package br.com.zup.desafios.proposta.config.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private List<String> messages;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status) {
        this();
        this.status = status;
        this.messages = List.of("Unexpected error");
    }

    ApiError(HttpStatus status, List<String> messages) {
        this();
        this.status = status;
        this.messages = messages;
    }

    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.messages = List.of(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
