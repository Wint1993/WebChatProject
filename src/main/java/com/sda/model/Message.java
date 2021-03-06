package com.sda.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime time;

    @Column
    private String timeString;

    @NotNull
    @Column
    private String message;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_from")
    private Client clientFrom;

    @ManyToOne
    @JoinColumn(name = "client_to")
    private Client clientTo;

 /*   public Message(Long id, String message, Client clientFrom, Client clientTo){
        this.id = id;
        this.message = message;
        this.clientFrom = clientFrom;
        this.clientTo = clientTo;
    }*/

    public Message(){}

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

    public Client getClientFrom() {
        return clientFrom;
    }

    public void setClientFrom(Client clientFrom) {
        this.clientFrom = clientFrom;
    }

    public Client getClientTo() {
        return clientTo;
    }

    public void setClientTo(Client clientTo) {
        this.clientTo = clientTo;
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
