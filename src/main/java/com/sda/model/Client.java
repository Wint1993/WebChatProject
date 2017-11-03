package com.sda.model;

import com.sda.dto.ClientDTO;
import com.sda.utils.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Błażej on 2017-07-26.
 */
@Entity
@Table(name = "Client")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String login;

    private String lastName;

    private String email1;

    @Column(unique = true)
    private String uuid;

    private String password;

    @OneToMany(mappedBy = "clientFrom", cascade = {CascadeType.ALL})
    private List<Message> messagesSent = new ArrayList<>();

 //   private List<Client> addedFriends = new ArrayList<>();

    public Client(Long id, String password, String firstName, String lastName, String email,String uuid){
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email1 = email;
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

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

/*    public List<Client> getAddedFriends() {
        return addedFriends;
    }

    public void setAddedFriends(List<Client> addedFriends) {
        this.addedFriends = addedFriends;
    }
*/



    public static class Builder{

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String uuid;

        private String password;

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

        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Client build(){
            return of(this);
        }

        private Client of(Builder builder){
            return new Client(id,firstName,lastName,email,uuid, password);
        }
    }




}

