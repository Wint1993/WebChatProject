package com.sda.dto;

import org.mapstruct.Mapping;

import java.time.LocalTime;

public class MessageDTO {

    private Long id;
    private String message;

    private ClientDTO from;
    private ClientDTO to;
   // private LocalTime localTime;

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

    /*public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }*/
}
