package com.sda.dto;

import org.mapstruct.Mapping;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MessageDTO {

    private Long id;
    private String message;
    private ClientDTO from;
    private ClientDTO to;
    private LocalDateTime time;
    private String timeString;

    public MessageDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClientDTO getFrom() {
        return from;
    }

    public void setFrom(ClientDTO from) {
        this.from = from;
    }

    public ClientDTO getTo() {
        return to;
    }

    public void setTo(ClientDTO to) {
        this.to = to;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
