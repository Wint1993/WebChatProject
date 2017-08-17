package com.sda.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Błażej on 2017-07-26.
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String uuid;

    @OneToMany(mappedBy = "clientFrom", cascade = {CascadeType.ALL})
    private List<Message> messagesSent = new ArrayList<>();


    public Client(Long id, String firstName, String lastName, String email,String uuid){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.uuid = uuid;
    }

    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Message> getMessagesSent() {
        return messagesSent;
    }
    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

   public static class Builder{

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String uuid;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder() {}

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }
        public Client build(){
            return of(this);
        }

        private Client of(Builder builder){
            return new Client(id,firstName,lastName,email,uuid);
        }
    }


}

