package com.sda.utils;

import com.sda.model.Client;
import com.sda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ClientValidator implements Validator{

    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
       Client client = (Client) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"login", "NotEmpty");
        if(client.getLogin().length()<6 || client.getLogin().length() > 16 ){
            errors.rejectValue("login","Size.loginForm.login");
        }
        if(clientService.findOne(client.getLogin()) != null){
            errors.rejectValue("login","Duplicate.clientForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (client.getPassword().length() < 8 || client.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!client.getPasswordConfirm().equals(client.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }


    }
}
