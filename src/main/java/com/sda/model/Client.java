package com.sda.model;

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

    private String passwordConfirm;

    private String lastName;

    private String email;

    @Column(unique = true)
    private String uuid;

    private String password;

    @Enumerated
    private UserRoles userRole;

    @OneToMany(mappedBy = "clientFrom", cascade = {CascadeType.ALL})
    private List<Message> messagesSent = new ArrayList<>();


    public Client(Long id, String password,String passwordConfirm, String firstName, String lastName, String email,String uuid){
        this.id = id;
        this.passwordConfirm = passwordConfirm;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.uuid = uuid;
    }

    public Client() {

    }
    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(userRole.name()));
        return list;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public static class Builder{

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String uuid;
        private String passwordConfirm;

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

        public Builder passwordConfirm(String passwordConfirm){
            this.passwordConfirm = passwordConfirm;
            return this;
        }


        public Client build(){
            return of(this);
        }

        private Client of(Builder builder){
            return new Client(id,firstName,lastName,email,uuid, password,passwordConfirm);
        }
    }




}

