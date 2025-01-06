package ru.itmo.reactivejava.payload.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
